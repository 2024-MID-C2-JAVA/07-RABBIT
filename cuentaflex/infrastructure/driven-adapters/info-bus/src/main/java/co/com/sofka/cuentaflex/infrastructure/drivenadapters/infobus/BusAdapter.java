package co.com.sofka.cuentaflex.infrastructure.drivenadapters.infobus;

import co.com.sofka.core.cryptography.aes.AESCipher;
import co.com.sofka.cuentaflex.domain.drivenports.messaging.BusPort;
import co.com.sofka.cuentaflex.domain.drivenports.messaging.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public final class BusAdapter implements BusPort {
    private final BusQueueParams busQueueParams;
    private final BusEncryptionParams busEncryptionParams;
    private final AmqpTemplate amqpTemplate;

    public BusAdapter(BusQueueParams busQueueParams, BusEncryptionParams busEncryptionParams, AmqpTemplate amqpTemplate) {
        this.busQueueParams = busQueueParams;
        this.busEncryptionParams = busEncryptionParams;
        this.amqpTemplate = amqpTemplate;
    }

    @Override
    public void send(Message message) {
        String iv = generateRandomIV();
        String symmetricKey = busEncryptionParams.getSymmetricKey();
        String jsonMessage = parseMessageToJson(message);
        String encryptedMessage = AESCipher.encryptToBase64(jsonMessage, symmetricKey, iv);

        String finalMessage = String.format("%s:%s", iv, encryptedMessage);

        amqpTemplate.convertAndSend(
                busQueueParams.getExchangeName(),
                busQueueParams.getRoutingKey(),
                finalMessage
        );
    }

    private String generateRandomIV() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return Base64.getEncoder().encodeToString(iv).substring(0, 16);
    }

    private String parseMessageToJson(Message message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
