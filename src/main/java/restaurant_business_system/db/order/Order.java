package restaurant_business_system.db.order;

import restaurant_business_system.resources.GenerateID;

public class Order {
    private String idOrder;
    private String idBill;
    private String idFood;
    private int quantity;
    private String status;

    public Order(String idOrder, String idBill, String idFood, int quantity, String status) {
        this.idOrder = idOrder;
        this.idBill = idBill;
        this.idFood = idFood;
        this.quantity = quantity;
        this.status = status;
    }

    public Order(String idBill, String idFood, int quantity) {
        this.idOrder = GenerateID.generateUniqueID();
        this.idBill = idBill;
        this.idFood = idFood;
        this.quantity = quantity;
        this.status = "Processing";
    }

    public Order() {
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public String getIdFood() {
        return idFood;
    }

    public void setIdFood(String idFood) {
        this.idFood = idFood;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
