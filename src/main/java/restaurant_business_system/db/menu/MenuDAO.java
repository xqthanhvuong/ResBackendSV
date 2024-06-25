package restaurant_business_system.db.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.jdbi.v3.core.Jdbi;

import jakarta.ws.rs.ForbiddenException;

/**
 * The MenuDAO class provides methods to interact with the menus table in the database.
 */
public class MenuDAO {
    private final Jdbi jdbi;

    /**
     * Constructs a new MenuDAO object.
     *
     * @param jdbi the Jdbi instance used to interact with the database
     */
    public MenuDAO(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    /**
     * Creates a new menu in the database.
     *
     * @param menu the menu object to be created
     * @return the created menu object
     */
    public Menu create(Menu menu) {
        jdbi.useHandle(handle -> handle.createUpdate("INSERT INTO menus (id_menu, name, id_restaurant, status) VALUES (:idMenu, :name, :idRestaurant, :status)")
                .bind("idMenu", menu.getIdMenu())
                .bind("name", menu.getName())
                .bind("idRestaurant", menu.getIdRestaurant())
                .bind("status", menu.getStatus())
                .execute());
        return menu;
    }
    /**
     * Updates an existing menu in the database.
     *
     * @param menu the menu object to be updated
     * @return the updated menu object
     */
    public Menu update(Menu menu, String idAccount) {
        jdbi.useHandle(handle -> {

            handle.createUpdate("UPDATE menus SET name = :name WHERE id_menu = :idMenu AND id_restaurant = :idRestaurant")
                .bind("idMenu", menu.getIdMenu())
                .bind("name", menu.getName())
                .bind("idRestaurant", menu.getIdRestaurant())
                .execute();
        });
        return menu;
    }  

    /**
     * Deletes a menu from the database.
     *
     * @param idMenu the ID of the menu to be deleted
     */
    public void delete(String idMenu, String idAccount) {
        jdbi.useHandle(handle -> {
            //Get id_restaurant from id_menu
            String idRestaurant = handle.createQuery("SELECT id_restaurant FROM menus WHERE id_menu = :idMenu")
                .bind("idMenu", idMenu)
                .mapTo(String.class)
                .one();

            // Set the status of the menu to inactive
            handle.createUpdate("Update menus set status = 'Inactive' WHERE id_menu = :idMenu")
                .bind("idMenu", idMenu)
                .execute();
        });
    }

    /**
     * Deletes all menus from the database.
     */
    public void deleteAll() {
        jdbi.useHandle(handle -> handle.createUpdate("DELETE FROM menus").execute());
    }

    /**
     * Gets a menu from the database.
     *
     * @param idRestaurant the ID of the menu to get
     * @return the menu object
     */
    public List<MenuDTO> get(String idRestaurant) {
        List<Map<String, Object>> menus = jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM menus WHERE id_restaurant = :idRestaurant AND status = 'Active'")
                .bind("idRestaurant", idRestaurant)
                .mapToMap()
                .list());
        List<MenuDTO> menuList = new ArrayList<>();
        for (Map<String, Object> menu : menus) {
            menuList.add(new MenuDTO((String) menu.get("id_menu"), (String) menu.get("name"), (String) menu.get("status")));
        }
        return menuList;
    }
}
