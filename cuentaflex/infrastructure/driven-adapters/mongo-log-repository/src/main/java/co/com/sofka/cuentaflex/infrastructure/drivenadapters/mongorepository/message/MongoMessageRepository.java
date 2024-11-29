package co.com.sofka.cuentaflex.infrastructure.drivenadapters.mongorepository.message;

import co.com.sofka.cuentaflex.domain.drivenports.messaging.Message;
import co.com.sofka.cuentaflex.domain.drivenports.messaging.MessageRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MongoMessageRepository implements MessageRepository {
    private final MongoTemplate mongoTemplate;

    public MongoMessageRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void saveMessage(Message message) {
        MessageDocument messageDocument = new MessageDocument(
                null,
                message.getMessage(),
                message.getDetail()
        );

        mongoTemplate.save(messageDocument);
    }
}
