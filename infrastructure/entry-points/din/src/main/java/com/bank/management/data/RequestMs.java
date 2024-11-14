package com.bank.management.data;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class RequestMs<T> {

    @NotNull(message = "DinHeader must be present.")
    private DinHeader dinHeader;
    @Valid
    private T dinBody;

    public RequestMs() {
    }

    public RequestMs(DinHeader dinHeader, T dinBody) {
        this.dinHeader = dinHeader;
        this.dinBody = dinBody;
    }

    public void validateDinHeaderFields() {
        if (dinHeader == null) {
            throw new IllegalArgumentException("DinHeader must be present.");
        }

        if (dinHeader.getDevice() == null || dinHeader.getDevice().isEmpty() ||
                dinHeader.getLanguage() == null || dinHeader.getLanguage().isEmpty() ||
                dinHeader.getUuid() == null || dinHeader.getUuid().isEmpty() ||
                dinHeader.getIp() == null || dinHeader.getIp().isEmpty() ||
                dinHeader.getTransactionTime() == null || dinHeader.getTransactionTime().isEmpty() ||
                dinHeader.getSymmetricKey() == null || dinHeader.getSymmetricKey().isEmpty() ||
                dinHeader.getInitializationVector() == null || dinHeader.getInitializationVector().isEmpty()) {

            throw new IllegalArgumentException("All fields in DinHeader must be complete.");
        }
    }

    public DinHeader getDinHeader() {
        return dinHeader;
    }

    public void setDinHeader(DinHeader dinHeader) {
        this.dinHeader = dinHeader;
    }

    public T getDinBody() {
        return dinBody;
    }

    public void setDinBody(T dinBody) {
        this.dinBody = dinBody;
    }
}