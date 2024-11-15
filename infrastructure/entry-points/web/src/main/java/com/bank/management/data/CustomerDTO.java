package com.bank.management.data;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for Customer.
 */
public class CustomerDTO {
    @NotBlank(message = "Username cannot be empty or null")
    private final String username;
    @NotBlank(message = "Name cannot be empty or null")
    private final String name;
    @NotBlank(message = "Lastname cannot be empty or null")
    private final String lastname;

    private CustomerDTO(Builder builder) {
        this.username = builder.username;
        this.name = builder.name;
        this.lastname = builder.lastname;
    }

    public @NotBlank(message = "Name cannot be empty or null") String getName() {
        return name;
    }

    public @NotBlank(message = "Lastname cannot be empty or null") String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public static class Builder {
        private String username;
        private String name;
        private String lastname;

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }


        public CustomerDTO build() {
            return new CustomerDTO(this);
        }
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "username='" + username + '\'' +
                '}';
    }
}
