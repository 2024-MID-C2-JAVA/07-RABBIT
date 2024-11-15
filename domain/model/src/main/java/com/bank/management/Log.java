package com.bank.management;

import java.util.List;

public class Log {

    private List<LogTransaction> logsSuccess;
    private List<String> logsError;

    public Log(List<LogTransaction> logsSuccess, List<String> logsError) {
        this.logsSuccess = logsSuccess;
        this.logsError = logsError;
    }

    public List<LogTransaction> getLogsSuccess() {
        return logsSuccess;
    }

    public void setLogsSuccess(List<LogTransaction> logsSuccess) {
        this.logsSuccess = logsSuccess;
    }

    public List<String> getLogsError() {
        return logsError;
    }

    public void setLogsError(List<String> logsError) {
        this.logsError = logsError;
    }
}
