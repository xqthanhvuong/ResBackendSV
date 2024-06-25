package restaurant_business_system.db.order;

public class FoodOrder {
    private String idOrder;
    private String idFood;
    private String idBill;
    private int quantity;
    private String status;

    public FoodOrder(String idOrder, String idFood, String idBill, int quantity) {
        this.idOrder = idOrder;
        this.idFood = idFood;
        this.idBill = idBill;
        this.quantity = quantity;
        this.status = "Processing";
    }

    public String getIdOrder() {
        return idOrder;
    }

    public String getIdFood() {
        return idFood;
    }

    public String getIdBill() {
        return idBill;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }
}
