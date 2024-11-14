package com.bank.management.data;

import jakarta.validation.constraints.NotBlank;

public class RequestCreateUserDTO {
    @NotBlank(message = "Username cannot be empty or null")
    private String username;
    @NotBlank(message = "Password cannot be empty or null")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
