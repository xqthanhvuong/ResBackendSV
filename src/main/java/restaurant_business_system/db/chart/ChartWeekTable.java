package restaurant_business_system.db.chart;

import java.util.ArrayList;
import java.util.List;

public class ChartWeekTable {
    private String idTable;
    private String tableName;
    private List<DayOfWeekSales> chartListLastWeek;
    private List<DayOfWeekSales> chartListThisWeek;



    public List<DayOfWeekSales> getChartListThisWeek() {
        return chartListThisWeek;
    }


    public void setChartListThisWeek(List<DayOfWeekSales> chartListThisWeek) {
        this.chartListThisWeek = chartListThisWeek;
    }


    public ChartWeekTable(String idTable, String tableName) {
        this.idTable = idTable;
        this.tableName = tableName;
        this.chartListLastWeek = new ArrayList<>();
        this.chartListThisWeek = new ArrayList<>();
    }


    public String getIdTable() {
        return idTable;
    }

    public void setIdTable(String idTable) {
        this.idTable = idTable;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<DayOfWeekSales> getChartListLastWeek() {
        return chartListLastWeek;
    }

    public void setChartListlastWeek(List<DayOfWeekSales> chartList) {
        this.chartListLastWeek = chartList;
    }

}
