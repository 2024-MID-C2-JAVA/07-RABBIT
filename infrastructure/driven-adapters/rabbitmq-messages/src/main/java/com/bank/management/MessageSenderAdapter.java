package com.bank.management;

import com.bank.management.gateway.MessageSenderGateway;
import com.bank.management.usecase.appservice.EncryptionUseCase;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderAdapter implements MessageSenderGateway {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${encryption.symmetricKey}")
    private String symmetricKey;

    @Value("${encryption.initializationVector}")
    private String initializationVector;


    private final RabbitTemplate rabbitTemplate;
    private final JsonMapper jsonMapper;
    private final EncryptionUseCase encryptionUseCase;

    public MessageSenderAdapter(RabbitTemplate rabbitTemplate, EncryptionUseCase encryptionUseCase) {
        this.rabbitTemplate = rabbitTemplate;
        this.encryptionUseCase = encryptionUseCase;
        this.jsonMapper = new JsonMapper();
    }

    @Override
    public void sendMessage(Transaction trx) {
        try {
            Log encryptedTransaction = new Log();

            encryptedTransaction.setId(encryptionUseCase.encryptData(trx.getId(), symmetricKey, initializationVector));
            encryptedTransaction.setTypeTransaction(encryptionUseCase.encryptData(trx.getTypeTransaction(), symmetricKey, initializationVector));
            encryptedTransaction.setAmountTransaction(encryptionUseCase.encryptData(trx.getAmountTransaction().toString(), symmetricKey, initializationVector));
            encryptedTransaction.setTransactionCost(encryptionUseCase.encryptData(trx.getTransactionCost().toString(), symmetricKey, initializationVector));
            encryptedTransaction.setTimeStamp(encryptionUseCase.encryptData(trx.getTimeStamp().toString(), symmetricKey, initializationVector));

            String jsonMessage = jsonMapper.writeValueAsString(encryptedTransaction);
            rabbitTemplate.convertAndSend(exchangeName, routingKey, jsonMessage);
        } catch (Exception e) {
           //QUE DEBERIA SUCEDER ACA?
        }
    }
}
