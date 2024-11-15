package com.bank.management.data;

import com.bank.management.LogTransaction;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "transactions")
public class LogDocument {

    @Id
    private String id;
    private List<LogTransaction> logsSuccess;
    private List<String> logsError;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
