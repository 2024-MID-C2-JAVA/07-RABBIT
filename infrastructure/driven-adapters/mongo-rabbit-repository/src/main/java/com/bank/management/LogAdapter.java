package com.bank.management;

import com.bank.management.data.LogDocument;
import com.bank.management.gateway.LogRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import com.bank.management.Log;

import java.util.Collections;

@Component
public class LogAdapter implements LogRepository {

    private final MongoTemplate mongoTemplate;

    public LogAdapter(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void saveLogSuccess(LogTransaction trx) {
        try {
            Query query = new Query(Criteria.where("_id").exists(true));
            Update update = new Update().push("logsSuccess", trx);
            mongoTemplate.upsert(query, update, LogDocument.class);
        } catch (Exception e) {
            throw new RuntimeException("Error saving success log", e);
        }
    }

    @Override
    public void saveLogError(String errorMessage) {
        try {
            Query query = new Query(Criteria.where("_id").exists(true));
            Update update = new Update().push("logsError", errorMessage);
            mongoTemplate.upsert(query, update, LogDocument.class);
        } catch (Exception e) {
            throw new RuntimeException("Error saving error log", e);
        }
    }

    @Override
    public Log getLog() {
        Query query = new Query(Criteria.where("_id").exists(true));
        LogDocument logDocument = mongoTemplate.findOne(query, LogDocument.class);

        if (logDocument == null) {
            return new Log(Collections.emptyList(), Collections.emptyList());
        }

        return new Log(
                logDocument.getLogsSuccess() != null ? logDocument.getLogsSuccess() : Collections.emptyList(),
                logDocument.getLogsError() != null ? logDocument.getLogsError() : Collections.emptyList()
        );
    }
}
