package restaurant_business_system.db.bill;

import restaurant_business_system.resources.GenerateID;

public class Bill {
    private String idBill;
    private String idTable;
    private String status;

    public Bill(String idBill, String idTable, String status) {
        this.idBill = idBill;
        this.idTable = idTable;
        this.status = status;
    }

    public Bill(String idTable) {
        this.idBill = GenerateID.generateUniqueID();
        this.idTable = idTable;
        this.status = "Open";
    }

    public Bill() {
    }

    public String getIdBill() {
        return idBill;
    }

    public void setIdBill(String idBill) {
        this.idBill = idBill;
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

}
