package restaurant_business_system.db.food;

import java.util.ArrayList;
import java.util.List;

public class FoodOrderDTO2 {
    private String idTable;
    private String nameTable;
    private String idBill;
    private List<FoodDetails2> foodDetails;
    private float total;

    public FoodOrderDTO2(String idTable, String idBill, String nameTable) {
        this.idTable = idTable;
        this.idBill = idBill;
        this.foodDetails = new ArrayList<>();
        this.nameTable = nameTable;
    }

    public void calculateTotal() {
        float subTotal = 0;
        for (FoodDetails2 foodDetail : foodDetails) {
            subTotal += (foodDetail.getQuantity() * foodDetail.getPrice());
        }
        this.total = subTotal;
    }

    public String getNameTable() {
        return nameTable;
    }

    public String getIdTable() {
        return idTable;
    }

    public String getIdBill() {
        return idBill;
    }

    public List<FoodDetails2> getFoodDetails() {
        return foodDetails;
    }

    public float getTotal() {
        return total;
    }

    public void addFoodDetails(FoodDetails2 foodDetails) {
        this.foodDetails.add(foodDetails);
    }
}
