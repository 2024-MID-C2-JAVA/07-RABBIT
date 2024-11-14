package com.bank.management.data;

public class ResponseMs<T> {
    private DinHeader dinHeader;
    private T dinBody;
    private DinError dinError;

    public ResponseMs() {
    }

    public ResponseMs(DinHeader dinHeader, T dinBody, DinError dinError) {
        this.dinHeader = dinHeader;
        this.dinBody = dinBody;
        this.dinError = dinError;
    }

    public ResponseMs(DinHeader dinHeader, DinError dinError) {
        this.dinHeader = dinHeader;
        this.dinError = dinError;
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

    public DinError getDinError() {
        return dinError;
    }

    public void setDinError(DinError dinError) {
        this.dinError = dinError;
    }
}