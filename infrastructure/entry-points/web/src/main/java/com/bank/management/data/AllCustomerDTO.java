package com.bank.management.data;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for Customer.
 */
public class AllCustomerDTO {
    @NotBlank(message = "Username cannot be empty or null")
    private final String username;
    @NotBlank(message = "Name cannot be empty or null")
    private final String name;
    @NotBlank(message = "Lastname cannot be empty or null")
    private final String lastname;
    @NotBlank(message = "ID cannot be empty or null")
    private final String id;

    private AllCustomerDTO(Builder builder) {
        this.username = builder.username;
        this.id = builder.id;
        this.name = builder.name;
        this.lastname = builder.lastname;
    }

    public @NotBlank(message = "Lastname cannot be empty or null") String getLastname() {
        return lastname;
    }

    public @NotBlank(message = "Name cannot be empty or null") String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {

        return username;
    }

    public static class Builder {
        private String username;
        private String name;
        private String lastname;
        private String id;

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }


        public AllCustomerDTO build() {

            return new AllCustomerDTO(this);
        }
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "username='" + username + '\'' +
                "id='" + id + '\'' +
                '}';
    }
}
