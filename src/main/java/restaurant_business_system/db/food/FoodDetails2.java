package restaurant_business_system.db.food;

public class FoodDetails2 {
    private String idFood;
    private String name;
    private double price;
    private String image;
    private int quantity;
    private String paid;

    public FoodDetails2(String idFood, String name, double price, String image, int quantity, String paid) {
        this.idFood = idFood;
        this.name = name;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.paid = paid;
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

    public String getPaid() {
        return paid;
    }
}
