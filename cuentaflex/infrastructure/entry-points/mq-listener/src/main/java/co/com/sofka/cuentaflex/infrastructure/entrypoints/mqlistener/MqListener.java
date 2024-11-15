package co.com.sofka.cuentaflex.infrastructure.entrypoints.mqlistener;

import co.com.sofka.core.cryptography.aes.AESCipher;
import co.com.sofka.cuentaflex.domain.drivenports.messaging.Listener;
import co.com.sofka.cuentaflex.domain.drivenports.messaging.Message;
import co.com.sofka.cuentaflex.domain.usecases.logservice.HandleMessageUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public final class MqListener implements Listener {
    private final HandleMessageUseCase handleMessageUseCase;
    private final MqListenerEncryptionParams mqListenerEncryptionParams;

    public MqListener(HandleMessageUseCase handleMessageUseCase, MqListenerEncryptionParams busEncryptionParams) {
        this.handleMessageUseCase = handleMessageUseCase;
        this.mqListenerEncryptionParams = busEncryptionParams;
    }

    @Override
    @RabbitListener(queues = "${listener.queue.name}")
    public void listen(String message) {
        String[] parts = message.split(":");
        String iv = parts[0];
        String encryptedMessage = parts[1];
        String decryptedMessage = AESCipher.decryptFromBase64(encryptedMessage, mqListenerEncryptionParams.getSymmetricKey(), iv);
        handleMessageUseCase.execute(parseMessageFromJson(decryptedMessage));
    }

    private Message parseMessageFromJson(String jsonMessage) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonMessage, Message.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
