package com.bank.management.data;

import jakarta.validation.constraints.NotBlank;

public class RequestGetCustomerDTO {
    @NotBlank(message = "ID cannot be empty or null")
    private String id;

    public RequestGetCustomerDTO() {}

    public RequestGetCustomerDTO(String id) {
        this.id = id;
    }

    // Getter y Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
