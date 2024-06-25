package restaurant_business_system.db.bill;

public class ObjectPayment {
    private String nameFood;
    private float price;
    private int quantity;
    private String code;
    private String idFood;

    public ObjectPayment(String nameFood, float price, int quantity, String code, String idFood) {
        this.nameFood = nameFood;
        this.price = price;
        this.quantity = quantity;
        this.code = code;
        this.idFood = idFood;
    }

    public String getNameFood() {
        return nameFood;
    }

    public float getPrice() {
        return price;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public String getCode() {
        return code;
    }

    public String getIdFood() {
        return idFood;
    }
}