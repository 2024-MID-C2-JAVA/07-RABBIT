package com.bank.management.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class RegisterRequestDTO {

    @NotBlank(message = "Name cannot be empty")
    @Schema(description = "First name of the user", example = "John", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank(message = "Last name cannot be empty")
    @Schema(description = "Last name of the user", example = "Doe", requiredMode = Schema.RequiredMode.REQUIRED)
    private String lastname;

    @NotBlank(message = "Username cannot be empty")
    @Schema(description = "Username for login", example = "johndoe", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Schema(description = "Password for login", example = "securePassword123", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

    @NotEmpty(message = "Roles cannot be empty")
    @Schema(description = "Roles of the user", example = "[ADMIN, CUSTOMER] or [CUSTOMER]", allowableValues = {"ADMIN", "CUSTOMER"}, requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> roles;

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
