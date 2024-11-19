package com.bank.management.data;

import io.swagger.v3.oas.annotations.media.Schema;

public class DinHeader {

    @Schema(description = "Device from which the request is sent", example = "postman")
    private String device;

    @Schema(description = "Language used in the request", example = "spa")
    private String language;

    @Schema(description = "Unique identifier for the transaction", example = "222")
    private String uuid;

    @Schema(description = "IP address of the device", example = "192.0.1.14")
    private String ip;

    @Schema(description = "Transaction timestamp", example = "22/12/2")
    private String transactionTime;

    @Schema(description = "Symmetric key for encryption", example = "no")
    private String symmetricKey;

    @Schema(description = "Initialization vector for encryption", example = "no")
    private String initializationVector;

    public DinHeader() {
    }

    public DinHeader(String device, String language, String uuid, String ip, String transactionTime,
                     String symmetricKey, String initializationVector) {
        this.device = device;
        this.language = language;
        this.uuid = uuid;
        this.ip = ip;
        this.transactionTime = transactionTime;
        this.symmetricKey = symmetricKey;
        this.initializationVector = initializationVector;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getSymmetricKey() {
        return symmetricKey;
    }

    public void setSymmetricKey(String symmetricKey) {
        this.symmetricKey = symmetricKey;
    }

    public String getInitializationVector() {
        return initializationVector;
    }

    public void setInitializationVector(String initializationVector) {
        this.initializationVector = initializationVector;
    }
}
