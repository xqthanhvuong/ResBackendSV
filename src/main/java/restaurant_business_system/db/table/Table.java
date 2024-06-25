package restaurant_business_system.db.table;

import restaurant_business_system.resources.GenerateID;

/**
 * The Table class represents a table in a restaurant.
 */
public class Table {
    private String idTable;
    private String idRestaurant;
    private String status;
    private String tableName;

    /**
     * Constructs a Table object with the specified table ID, restaurant ID, and status.
     * 
     * @param idTable     the ID of the table
     * @param idRestaurant the ID of the restaurant
     * @param status      the status of the table
     * @param tableName   the name of the table
     */
    public Table(String idTable, String idRestaurant, String status, String tableName) {
        this.idTable = idTable;
        this.idRestaurant = idRestaurant;
        this.status = status;
        this.tableName = tableName;
    }

    /**
     * Constructs a Table object with the specified restaurant ID.
     * The table ID is generated automatically.
     * The initial status of the table is set to "Available".
     * 
     * @param idRestaurant the ID of the restaurant
     */
    public Table(String idRestaurant, String tableName) {
        this.idTable = GenerateID.generateUniqueID();
        this.idRestaurant = idRestaurant;
        this.status = "Available";
        this.tableName = tableName;
    }

    /**
     * Constructs an empty Table object.
     */
    public Table() {
    }

    /**
     * Returns the ID of the table.
     * 
     * @return the ID of the table
     */
    public String getIdTable() {
        return idTable;
    }

    /**
     * Sets the ID of the table.
     * 
     * @param idTable the ID of the table
     */
    public void setIdTable(String idTable) {
        this.idTable = idTable;
    }

    /**
     * Returns the ID of the restaurant.
     * 
     * @return the ID of the restaurant
     */
    public String getIdRestaurant() {
        return idRestaurant;
    }

    /**
     * Sets the ID of the restaurant.
     * 
     * @param idRestaurant the ID of the restaurant
     */
    public void setIdRestaurant(String idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    /**
     * Returns the status of the table.
     * 
     * @return the status of the table
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the table.
     * 
     * @param status the status of the table
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the name of the table.
     * 
     * @return the name of the table
     */
    public String getTableName() {
        return tableName;
    }
}
