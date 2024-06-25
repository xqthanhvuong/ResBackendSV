package restaurant_business_system.db.food;

import restaurant_business_system.resources.GenerateID;

/*
 * The Food class represents a food item in the system.
 */
public class Food {
    private String idFood;
    private String idMenu;
    private String name;
    private double price = 0.0;
    private String image;

    public Food(String idMenu, String name, double price, String image) {
        this.idFood = GenerateID.generateUniqueID();
        this.idMenu = idMenu;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Food(String idFood, String idMenu, String name, double price, String image) {
        this.idFood = idFood;
        this.idMenu = idMenu;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Food() {
    }

    public String getIdFood() {
        return idFood;
    }

    public String getIdMenu() {
        return idMenu;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public void setIdFood(String idFood) {
        this.idFood = idFood;
    }

    public void setIdMenu(String idMenu) {
        this.idMenu = idMenu;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
