package restaurant_business_system.db.food;

import java.util.ArrayList;
import java.util.List;

public class FoodOrderDTO {
    private String idTable;
    private String nameTable;
    private String idBill;
    private List<FoodDetails> foodDetails;
    private float total;

    public FoodOrderDTO(String idTable, String idBill, String nameTable) {
        this.idTable = idTable;
        this.idBill = idBill;
        this.foodDetails = new ArrayList<>();
        this.nameTable = nameTable;
    }

    public void calculateTotal() {
        float subTotal = 0;
        for (FoodDetails foodDetail : foodDetails) {
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

    public List<FoodDetails> getFoodDetails() {
        return foodDetails;
    }

    public void setIdTable(String idTable) {
        this.idTable = idTable;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
    }

    public float getTotal() {
        return total;
    }

    public void setFoodDetails(List<FoodDetails> foodDetails) {
        this.foodDetails = foodDetails;
    }

    public void addFoodDetails(FoodDetails foodDetails) {
        this.foodDetails.add(foodDetails);
    }
}
