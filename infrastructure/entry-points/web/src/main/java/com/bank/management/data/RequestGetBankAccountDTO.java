package com.bank.management.data;

import jakarta.validation.constraints.NotBlank;

public class RequestGetBankAccountDTO {
    @NotBlank(message = "ID cannot be empty or null")
    private String id;

    public RequestGetBankAccountDTO() {}

    public RequestGetBankAccountDTO(String id) {
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
