package co.sofka;

import java.util.List;

public class Customer {

    private String id;

    private String username;

    private String pwd;

    private List<Account> accounts;

    public Customer(String id, String username, List<Account> accounts) {
        this.id = id;
        this.username = username;
        this.accounts = accounts;
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
