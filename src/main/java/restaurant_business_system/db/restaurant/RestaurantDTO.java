package restaurant_business_system.db.restaurant;

public class RestaurantDTO {
    private String idRestaurant;
    private String name;
    private String status;

    public RestaurantDTO(String idRestaurant, String name, String status) {
        this.idRestaurant = idRestaurant;
        this.name = name;
        this.status = status;
    }

    public String getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(String idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
