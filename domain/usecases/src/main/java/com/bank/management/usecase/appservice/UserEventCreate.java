package com.bank.management.usecase.appservice;

import java.util.List;

public class UserEventCreate {

    private String id;
    private String name;
    private String lastname;
    private String username;
    private String password;
    private List<String> roles;

    private UserEventCreate(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.name = builder.name;
        this.lastname = builder.lastname;
        this.roles = builder.roles;
        this.id = builder.id;
    }

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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
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

    public void setUsername(String user) {
        this.username = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String name;
        private String lastname;
        private String username;
        private String password;
        private List<String> roles;


        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder roles(List<String> roles) {
            this.roles = roles;
            return this;
        }

        public UserEventCreate build() {
            return new UserEventCreate(this);
        }
    }
}
