package restaurant_business_system.db.food;

public class FoodDTO3 {
    private String idFood;
    private String name;
    private String image;
    private String statusOrder;
    private int quantity;
    private String statusPayment;

    public FoodDTO3(String idFood, String name, String image, int quantity, String statusOrder, String statusPayment) {
        this.idFood = idFood;
        this.name = name;
        this.image = image;
        this.statusOrder = statusOrder;
        this.quantity = quantity;
        this.statusPayment = statusPayment;
    }

    public String getIdFood() {
        return idFood;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatusOrder () {
        return statusOrder;
    }

    public String getStatusPayment() {
        return statusPayment;
    }
}
