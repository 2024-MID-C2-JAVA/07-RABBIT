package com.bank.management.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DinError {

    private String type;
    private String date;
    private String origin;
    private String code;
    private String providerErrorCode;
    private String message;
    private String details;

    public DinError(String type, String origin, String code, String providerErrorCode, String message, String details) {
        this.type = type;
        setCurrentDate();
        this.origin = origin;
        this.code = code;
        this.providerErrorCode = providerErrorCode;
        this.message = message;
        this.details = details;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProviderErrorCode() {
        return providerErrorCode;
    }

    public void setProviderErrorCode(String providerErrorCode) {
        this.providerErrorCode = providerErrorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    private void setCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        this.date = dateFormat.format(new Date());
    }
}
