package restaurant_business_system.map;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import restaurant_business_system.db.chart.DayOfWeekSales;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DayOfWeekMapper implements RowMapper<DayOfWeekSales> {
    @Override
    public DayOfWeekSales map(ResultSet rs, StatementContext ctx) throws SQLException {
        DayOfWeekSales days = new DayOfWeekSales();
        days.setDayOfWeek(Integer.parseInt(rs.getString("day_of_week")));
        days.setTotalSales(Double.parseDouble(rs.getString("total_sales")));
        return days;
    }
}
