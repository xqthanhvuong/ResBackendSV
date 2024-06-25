package restaurant_business_system.db.chart;

public class ChartRequestMonth {
    private String idRestaurant;
    private int month;
    private int year;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ChartRequestMonth(String idRestaurant, int month, int year) {
        this.idRestaurant = idRestaurant;
        this.month = month;
        this.year = year;
    }

    public String getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(String idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public ChartRequestMonth() {
    }

}
