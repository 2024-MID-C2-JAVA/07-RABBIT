package co.sofka;

import java.util.List;

public class Customer {

    private String id;

    private String username;

    private String pwd;

    private String rol;

    private List<Account> accounts;

    public Customer(String id, String username, String pwd, String rol, List<Account> accounts) {
        this.id = id;
        this.username = username;
        this.pwd = pwd;
        this.rol = rol;
        this.accounts = accounts;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Customer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }


    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
