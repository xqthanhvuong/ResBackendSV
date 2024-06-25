package restaurant_business_system.db.chart;

public class Chart {
    private int day;
    private double totalPrice;

    public Chart(int day, double totalPrice) {
        this.day = day;
        this.totalPrice = totalPrice;
    }
    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public Chart(){}

}
