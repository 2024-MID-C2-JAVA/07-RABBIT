package com.bank.management.data;

import jakarta.validation.constraints.NotBlank;

public class RequestCreateCustomerDTO {


    @NotBlank(message = "Username cannot be empty or null")
    private String username;

    public RequestCreateCustomerDTO() {
    }

    public RequestCreateCustomerDTO(String username) {
        this.username = username;
    }

    private RequestCreateCustomerDTO(Builder builder) {
        this.username = builder.username;
    }

    public String getUsername() {
        return username;
    }

    public static class Builder {
        private String username;

        public Builder setUsername(String username) {
            this.username = username;
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
