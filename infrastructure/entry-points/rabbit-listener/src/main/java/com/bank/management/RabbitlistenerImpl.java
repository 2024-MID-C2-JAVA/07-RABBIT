package com.bank.management;

import com.bank.management.gateway.MessageListenerGateway;
import com.bank.management.usecase.logservice.EncryptionUseCase;
import com.bank.management.usecase.logservice.SaveLogErrorUseCase;
import com.bank.management.usecase.logservice.SaveLogSucessUseCase;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitListenerImpl implements MessageListenerGateway {


    @Value("${encryption.initializationVector}")
    private String symmetricKey;

    @Value("${encryption.symmetricKey}")
    private String initializationVector;

    private final SaveLogSucessUseCase saveLogSuccess;
    private final SaveLogErrorUseCase saveLogError;
    private final EncryptionUseCase encryptionUseCase;
    private final JsonMapper jsonMapper;

    public RabbitListenerImpl(SaveLogSucessUseCase saveLogSuccess, SaveLogErrorUseCase saveLogError, EncryptionUseCase encryptionUseCase) {
        this.saveLogSuccess = saveLogSuccess;
        this.saveLogError = saveLogError;
        this.encryptionUseCase = encryptionUseCase;
        this.jsonMapper = new JsonMapper();
    }

    @Override
    @RabbitListener(queues = "#{@queueNameSuccess}")
    public void receiveTransactionSuccess(String message) {
        try {
            LogTransaction encryptedLogTransaction = jsonMapper.readValue(message, LogTransaction.class);

            encryptedLogTransaction.setId(encryptionUseCase.decryptData(encryptedLogTransaction.getId(), symmetricKey, initializationVector));
            encryptedLogTransaction.setTypeTransaction(encryptionUseCase.decryptData(encryptedLogTransaction.getTypeTransaction(), symmetricKey, initializationVector));
            encryptedLogTransaction.setAmountTransaction(encryptionUseCase.decryptData(encryptedLogTransaction.getAmountTransaction(), symmetricKey, initializationVector));
            encryptedLogTransaction.setTransactionCost(encryptionUseCase.decryptData(encryptedLogTransaction.getTransactionCost(), symmetricKey, initializationVector));
            encryptedLogTransaction.setTimeStamp(encryptionUseCase.decryptData(encryptedLogTransaction.getTimeStamp(), symmetricKey, initializationVector));

            saveLogSuccess.apply(encryptedLogTransaction);
        } catch (Exception e) {
            throw new IllegalStateException("Error receiving and decrypting transaction message", e);
        }
    }

    @Override
    @RabbitListener(queues = "#{@queueNameError}")
    public void receiveMessageError(String message) {
        saveLogError.apply(message);
    }
}
