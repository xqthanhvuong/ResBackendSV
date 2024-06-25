package restaurant_business_system.db.bill;

public class OrderReturn {
    private String nametable;
    private String idOrder;
    private String idBill;

    public OrderReturn(String nametable, String idOrder, String idBill) {
        this.nametable = nametable;
        this.idOrder = idOrder;
        this.idBill = idBill;
    }

    public String getNametable() {
        return nametable;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public String getIdBill() {
        return idBill;
    }
}