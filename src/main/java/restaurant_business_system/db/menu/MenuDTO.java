package restaurant_business_system.db.menu;

public class MenuDTO {
    private String idMenu;
    private String name;
    private String status;

    public MenuDTO(String idMenu, String name, String status) {
        this.idMenu = idMenu;
        this.name = name;
        this.status = status;
    }


    public String getIdMenu() {
        return idMenu;
    }

    public String getName() {
        return name;
    }

    public void setIdMenu(String idMenu) {
        this.idMenu = idMenu;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
