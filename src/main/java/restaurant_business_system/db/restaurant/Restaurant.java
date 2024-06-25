package restaurant_business_system.db.restaurant;

import restaurant_business_system.resources.GenerateID;

public class Restaurant {

    private String idRestaurant;

    private String idAccount;

    private String name;

    private String status;

    /*
     * Constructors
     */
    public Restaurant() {
        // Default constructor
    }

    public Restaurant(String name, String idAccount) {
        this.idRestaurant = GenerateID.generateUniqueID();
        this.name = name;
        this.status = "active";
        this.idAccount = idAccount;
    }
    // Getters and Setters
    public String getIdRestaurant() {
        return idRestaurant;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }
}
