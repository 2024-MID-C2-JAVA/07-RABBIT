package com.bank.management.data;

public class DinHeader {

    private String device;
    private String language;
    private String uuid;
    private String ip;
    private String transactionTime;
    private String symmetricKey;
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
