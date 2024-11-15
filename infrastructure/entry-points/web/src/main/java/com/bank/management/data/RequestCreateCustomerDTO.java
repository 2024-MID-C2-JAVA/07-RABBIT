package com.bank.management.data;

import jakarta.validation.constraints.NotBlank;

public class RequestCreateCustomerDTO {


    @NotBlank(message = "Username cannot be empty or null")
    private String username;

    @NotBlank(message = "Name cannot be empty or null")
    private String name;

    @NotBlank(message = "Lastname cannot be empty or null")
    private String lastname;

    public RequestCreateCustomerDTO() {
    }

    public RequestCreateCustomerDTO(String username) {
        this.username = username;
    }

    private RequestCreateCustomerDTO(Builder builder) {

        this.username = builder.username;
        this.name = builder.name;
        this.lastname = builder.lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
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

        public RequestCreateCustomerDTO build() {
            return new RequestCreateCustomerDTO(this);
        }
    }

    @Override
    public String toString() {
        return "RequestCreateCustomerDTO{" +
                "username='" + username + '\'' +
                '}';
    }
}
