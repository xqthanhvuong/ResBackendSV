package restaurant_business_system.core;

import java.security.Principal;

public class User implements Principal {
    private final String username;
    private String idAccount;
    private boolean isLogged;

    public User(String username, String idAccount, boolean isLogged) {
        this.username = username;
        this.idAccount = idAccount;
        this.isLogged = isLogged;
    }

    public User(String username, String idAccount) {
        this.username = username;
        this.idAccount = idAccount;
    }

    public String getName() {
        return username;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }

    public String getId() {
        return idAccount;
    }

    public void setId(String id) {
        this.idAccount = id;
    }
}
