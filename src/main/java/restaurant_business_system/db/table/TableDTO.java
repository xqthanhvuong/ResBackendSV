package restaurant_business_system.db.table;

public class TableDTO {
    private String idTable;
    private String status;
    private String tableName;

    public TableDTO(String idTable, String status, String tableName) {
        this.idTable = idTable;
        this.status = status;
        this.tableName = tableName;
    }

    public String getIdTable() {
        return idTable;
    }

    public void setIdTable(String idTable) {
        this.idTable = idTable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTableName() {
        return tableName;
    }
}
