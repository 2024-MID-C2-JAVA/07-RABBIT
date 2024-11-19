package com.bank.management;

public class UserCreatedEvent {

    private final Long userId;
    private final String username;
    private final String password;

    public UserCreatedEvent(Long userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
