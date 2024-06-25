package restaurant_business_system.db.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import jakarta.ws.rs.ForbiddenException;

/**
 * The TableDAO class provides methods for accessing and manipulating table data in the database.
 */
public class TableDAO {
    private final Jdbi jdbi;

    public TableDAO(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    /**
     * Check if the user is the owner of the table.
     * 
     * @param idTable   the id of the table
     * @param idAccount the id of the account
     * @return true if the user is the owner of the table, false otherwise
     */
    private boolean isOwner(String idTable, String idAccount) {
        return jdbi.withHandle(handle -> {
            // Get idRestaurant from idTable
            Optional<String> idRestaurant = handle
                    .createQuery("SELECT id_restaurant FROM tables WHERE id_table = :idTable")
                    .bind("idTable", idTable)
                    .mapTo(String.class)
                    .findOne();
            if (idRestaurant.isEmpty()) {
                return false;
            }
            // Check if idAccount is the owner of idRestaurant
            Optional<String> idAccountRS = handle
                    .createQuery("SELECT id_account FROM restaurants WHERE id_restaurant = :idRestaurant")
                    .bind("idRestaurant", idRestaurant)
                    .mapTo(String.class)
                    .findOne();
            if (idAccountRS.isEmpty()) {
                return false;
            }
            return idAccount.equals(idAccountRS.get());
        });
    }

    /**
     * Check if the user is the owner of the restaurant.
     * 
     * @param idRestaurant the id of the restaurant
     * @param idAccount    the id of the account
     * @return true if the user is the owner of the restaurant, false otherwise
     */
    private boolean isOwnerRestaurant(String idRestaurant, String idAccount) {
        return jdbi.withHandle(handle -> {
            Optional<String> idAccountRS = handle
                    .createQuery("SELECT id_account FROM accounts WHERE id_restaurant = :idRestaurant and id_account = :idAccount")
                    .bind("idRestaurant", idRestaurant)
                    .bind("idAccount", idAccount)
                    .mapTo(String.class)
                    .findOne();
            return idAccountRS.isPresent();
        });
    }

    /**
     * Create a new table.
     * 
     * @param t         the table to create
     * @param idAccount the id of the account
     * @return the created table
     */
    public Table create(Table t, String idAccount) {
        //Check if idRestaurant is owned by idAccount
        if (!isOwnerRestaurant(t.getIdRestaurant(), idAccount)) {
            throw new ForbiddenException("You are not the owner of the restaurant");
        }

        jdbi.useHandle(handle -> {
            handle.createUpdate(
                    "INSERT INTO tables (id_table, id_restaurant, status, name_table) VALUES (:idTable, :idRestaurant, :status, :nameTable)")
                    .bind("idTable", t.getIdTable())
                    .bind("idRestaurant", t.getIdRestaurant())
                    .bind("status", t.getStatus())
                    .bind("nameTable", t.getTableName())
                    .execute();
        });
        return t;
    }

    /**
     * Update the status of a table.
     * 
     * @param idTable the id of the table
     * @param status  the new status of the table
     * @param idAccount the id of the account
     */
    @SqlUpdate("DELETE FROM tables WHERE id_table = :idTable")
    public void delete(String idTable, String idAccount) {
        // Check if the user is the owner of the table
        if (!isOwner(idTable, idAccount)) {
            throw new ForbiddenException("You are not the owner of the table");
        }

        jdbi.useHandle(handle -> {
            handle.createUpdate("DELETE FROM tables WHERE id_table = :idTable")
                    .bind("idTable", idTable)
                    .execute();
        });
    }

    /**
     * Get all tables of a restaurant.
     * 
     * @param idRestaurant the id of the restaurant
     * @param idAccount    the id of the account
     * @return a list of tables of the restaurant
     */
    public List<TableDTO> get(String idRestaurant, String idAccount) {
        //Get id account from id restaurant
        String idAccountRS = jdbi.withHandle(handle -> handle.createQuery("SELECT id_account FROM accounts WHERE id_restaurant = :idRestaurant and id_account = :idAccount")
                        .bind("idRestaurant", idRestaurant)
                        .bind("idAccount", idAccount)
                        .mapTo(String.class)
                        .one());
        
        //Check if idAccount is the owner of idRestaurant
        if (idAccountRS.isEmpty()) {
            throw new ForbiddenException("You are not the owner of the restaurant");
        }

        List<Map<String, Object>> result = jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM tables WHERE id_restaurant = :idRestaurant ORDER BY created_at ASC")
                        .bind("idRestaurant", idRestaurant)
                        .mapToMap()
                        .list());
        List<TableDTO> tables = new ArrayList<>();
        for (Map<String, Object> r : result) {
            tables.add(new TableDTO((String) r.get("id_table"), (String) r.get("status"), (String) r.get("name_table")));
        }
        return tables;
    }
}
