package restaurant_business_system.db.chart;

public class TableChart {
    private String id_table;
    private String name_table;

    // Các phương thức getter và setter (hoặc lombok annotations) cho các thuộc tính
    public TableChart(){}

    public String getId_table() {
        return id_table;
    }

    public void setId_table(String id_table) {
        this.id_table = id_table;
    }

    public String getName_table() {
        return name_table;
    }

    public void setName_table(String name_table) {
        this.name_table = name_table;
    }
}

