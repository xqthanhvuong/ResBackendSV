package restaurant_business_system.db.bill;

import java.util.List;

import restaurant_business_system.db.food.FoodDetails;

public class BillDTO {
    private String idTable;
    private List<FoodDetails> foods;
    private double total;
    private String status;

    public BillDTO(String idTable, List<FoodDetails> foods, String status) {
        this.idTable = idTable;
        this.foods = foods;
        double total = 0;
        for (FoodDetails food : foods) {
            total += food.getPrice() * food.getQuantity();
        }
        this.total = total;
        this.status = status;
    }

    public String getIdTable() {
        return idTable;
    }

    public List<FoodDetails> getFoods() {
        return foods;
    }

    public double getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }
}
