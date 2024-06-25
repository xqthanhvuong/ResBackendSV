package restaurant_business_system.db.food;

/**
 * The FoodDTO class represents a food item in the restaurant business system.
 * It contains information such as the food's ID, name, price, and image.
 */
class FoodDTO {
    private String idFood;
    private String name;
    private Float price;
    private String image;

    // constructor, getters, setters

    /**
     * Constructs a new FoodDTO object with the specified ID, name, price, and image.
     *
     * @param idFood The ID of the food.
     * @param name   The name of the food.
     * @param price  The price of the food.
     * @param image  The image of the food.
     */
    public FoodDTO(String idFood, String name, Float price, String image) {
        this.idFood = idFood;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    /**
     * Returns the ID of the food.
     *
     * @return The ID of the food.
     */
    public String getIdFood() {
        return idFood;
    }

    /**
     * Sets the ID of the food.
     *
     * @param idFood The ID of the food.
     */
    public void setIdFood(String idFood) {
        this.idFood = idFood;
    }

    /**
     * Returns the name of the food.
     *
     * @return The name of the food.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the food.
     *
     * @param name The name of the food.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the price of the food.
     *
     * @return The price of the food.
     */
    public Float getPrice() {
        return price;
    }

    /**
     * Sets the price of the food.
     *
     * @param price The price of the food.
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * Returns the image of the food.
     *
     * @return The image of the food.
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the image of the food.
     *
     * @param image The image of the food.
     */
    public void setImage(String image) {
        this.image = image;
    }
}
