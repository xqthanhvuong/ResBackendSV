package restaurant_business_system.db.account;

import restaurant_business_system.resources.GenerateID;

public class Account {
    private String id;

    private String username;

    private String password;

    private String role;

    private String name;

    private String phone;

    private String status;

    private String idRestaurant = null;
    /**
     * Constructs a new Account object.
     */
    public Account(String username, String password, String name, String phone) {
        this.id = GenerateID.generateUniqueID();
        this.username = username;
        this.password = password;
        this.role = "user";
        this.name = name;
        this.status = "inactive"; // "active" or "inactive
        this.phone = phone;
    }

    public Account(String username, String password, String name, String phone, String role, String idRestaurant) {
        this.id = GenerateID.generateUniqueID();
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = name;
        this.status = "inactive"; // "active" or "inactive
        this.phone = phone;
        this.idRestaurant = idRestaurant;
    }

    public Account(String id, String username, String name, String phone, String role) {
        this.username = username;
        this.name=name;
        this.phone = phone;
        this.role = role;
        this.id = id;
    }
    public Account() {}

    public Account(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdRestaurant() {
        return idRestaurant;
    }
}