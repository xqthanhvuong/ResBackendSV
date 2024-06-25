package restaurant_business_system.db.food;

public class FoodDetails {
    private String idFood;
    private String name;
    private double price;
    private String image;
    private int quantity;
    private String idOrder;

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public FoodDetails(String idOrder,String idFood, String name, double price,String image, int quantity) {
        this.idOrder = idOrder;
        this.idFood = idFood;
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }

    public FoodDetails(String idFood, String name, double price, int quantity) {
        this.idFood = idFood;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public FoodDetails(String idFood, String name, double price, String image, int quantity) {
        this.idFood = idFood;
        this.name = name;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public String getIdFood() {
        return idFood;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
