package restaurant_business_system.db.chart;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.jdbi.v3.core.Jdbi;

import restaurant_business_system.helper.DateTimeHelper;

public class ChartDAO {
    private final Jdbi jdbi;

    public ChartDAO(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public List<Chart> getMonthlyChart(ChartRequestMonth chartRequest) {
        List<Map<String, Object>> result = jdbi.withHandle(handle -> handle.createQuery("SELECT" +
                " DAY(b.created_at) AS transaction_date, " +
                " SUM(f.price * o.quantity) AS total_price " +
                " FROM bills b " +
                " JOIN orders o ON b.id_bill = o.id_bill " +
                " JOIN foods f ON o.id_food = f.id_food " +
                " join menus m on f.id_menu = m.id_menu " +
                " WHERE " +
                " b.status = 'Close' AND " +
                " MONTH(b.created_at) = :MONTH AND " +
                " YEAR(b.created_at) = :YEAR AND " +
                " m.id_restaurant = :id_restaurant " +
                " GROUP BY DAY(b.created_at) " +
                " ORDER BY transaction_date;")
                .bind("MONTH", chartRequest.getMonth())
                .bind("YEAR", chartRequest.getYear())
                .bind("id_restaurant", chartRequest.getIdRestaurant())
                .mapToMap()
                .list());
        System.out.println(chartRequest.getMonth());
        System.out.println(chartRequest.getYear());
        System.out.println(chartRequest.getIdRestaurant());
        List<Chart> charts = new ArrayList<>();
        for (Map<String, Object> row : result) {
            charts.add(new Chart((int) row.get("transaction_date"), (double) row.get("total_price")));
        }

        return charts;
    }

    public List<ChartWeekTable> getWeekChartForTable(String idRestaurant) {
        String lastWeekStart = DateTimeHelper.getSundayOfTwoWeekAgo();
        String lastWeekEnd = DateTimeHelper.getSundayOfLastWeek();
        String thisWeekStart = DateTimeHelper.getSundayOfLastWeek();
        String thisWeekEnd = DateTimeHelper.getSundayOfWeek();

        List<ChartWeekTable> charts = new ArrayList<>();
        List<TableChart> tables = jdbi.withHandle(handle -> handle.createQuery("SELECT t.id_table, t.name_table " +
                "FROM tables t " +
                "LEFT JOIN ( " +
                "    SELECT b.id_table, SUM(o.quantity * (SELECT price FROM foods WHERE id_food = o.id_food)) AS total_sales "
                +
                "    FROM orders o " +
                "    INNER JOIN bills b ON o.id_bill = b.id_bill " +
                "    WHERE b.created_at BETWEEN :lastWeekStart AND :thisWeekEnd " +
                "    GROUP BY b.id_table " +
                ") AS table_sales ON t.id_table = table_sales.id_table " +
                "WHERE t.id_restaurant = :idRestaurant " +
                "ORDER BY COALESCE(table_sales.total_sales, 0) DESC " +
                "LIMIT 3")
                .bind("lastWeekStart", lastWeekStart)
                .bind("thisWeekEnd", thisWeekEnd)
                .bind("idRestaurant", idRestaurant)
                .mapTo(TableChart.class)
                .list());
        for (TableChart table : tables) {
            charts.add(new ChartWeekTable(table.getId_table(), table.getName_table()));
        }
        for (ChartWeekTable table : charts) {
            List<DayOfWeekSales> results = jdbi.withHandle(handle -> handle
                    .createQuery("SELECT DAYOFWEEK(DATE(b.created_at)) AS day_of_week, " +
                            "SUM(f.price * o.quantity) AS total_sales " +
                            "FROM bills b " +
                            "JOIN orders o ON b.id_bill = o.id_bill " +
                            "JOIN foods f ON o.id_food = f.id_food " +
                            "WHERE b.status = 'Close' " +
                            "AND b.created_at BETWEEN :start AND :end " +
                            "AND b.id_table = :idTable " +
                            "GROUP BY DAYOFWEEK(DATE(b.created_at)) " +
                            "ORDER BY day_of_week")
                    .bind("start", lastWeekStart)
                    .bind("end", lastWeekEnd)
                    .bind("idTable", table.getIdTable())
                    .mapTo(DayOfWeekSales.class)
                    .list());
            table.setChartListlastWeek(results);

            List<DayOfWeekSales> results2 = jdbi.withHandle(handle -> handle
                    .createQuery("SELECT DAYOFWEEK(DATE(b.created_at)) AS day_of_week, " +
                            "SUM(f.price * o.quantity) AS total_sales " +
                            "FROM bills b " +
                            "JOIN orders o ON b.id_bill = o.id_bill " +
                            "JOIN foods f ON o.id_food = f.id_food " +
                            "WHERE b.status = 'Close' " +
                            "AND b.created_at BETWEEN :start AND :end " +
                            "AND b.id_table = :idTable " +
                            "GROUP BY DAYOFWEEK(DATE(b.created_at)) " +
                            "ORDER BY day_of_week")
                    .bind("start", thisWeekStart)
                    .bind("end", thisWeekEnd)
                    .bind("idTable", table.getIdTable())
                    .mapTo(DayOfWeekSales.class)
                    .list());
            table.setChartListThisWeek(results2);
        }
        return charts;
    }


}
