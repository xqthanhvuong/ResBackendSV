package restaurant_business_system.db.order;

import restaurant_business_system.resources.GenerateID;

public class OrderRequest {
    private String idBill;
    private String idRestaurant;
    private String idTable;
    private String idFood;
    private int quantity;

    public OrderRequest() {
    }

    public OrderRequest(String idBill, String idRestaurant, String idFood, int quantity) {
        this.idBill = idBill;
        this.idRestaurant = idRestaurant;
        this.idFood = idFood;
        this.quantity = quantity;
    }

    public OrderRequest(String idRestaurant, String idFood, int quantity, String idTable) {
        this.idBill = GenerateID.generateUniqueID();
        this.idRestaurant = idRestaurant;
        this.idFood = idFood;
        this.quantity = quantity;
        this.idTable = idTable;
    }

    // getters and setters
    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public String getIdFood() {
        return idFood;
    }

    public String getIdRestaurant() {
        return idRestaurant;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getIdTable() {
        return idTable;
    }
}
