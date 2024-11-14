package com.bank.management;

import com.bank.management.data.LogDocument;
import com.bank.management.gateway.LogRepository;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class LogAdapter implements LogRepository {

    private final MongoTemplate mongoTemplate;
    private final JsonMapper jsonMapper;

    public LogAdapter(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.jsonMapper = new JsonMapper();
    }

    @Override
    public void saveLog(String trx) {
        try {
            LogDocument logDocument = jsonMapper.readValue(trx, LogDocument.class);

            mongoTemplate.save(logDocument);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
