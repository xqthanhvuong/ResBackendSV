package restaurant_business_system.map;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import restaurant_business_system.db.chart.TableChart;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableChartMapper implements RowMapper<TableChart> {
    @Override
    public TableChart map(ResultSet rs, StatementContext ctx) throws SQLException {
        TableChart table = new TableChart();
        table.setId_table(rs.getString("id_table"));
        table.setName_table(rs.getString("name_table"));
        return table;
    }
}
