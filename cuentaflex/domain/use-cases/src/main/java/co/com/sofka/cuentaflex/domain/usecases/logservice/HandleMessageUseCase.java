package co.com.sofka.cuentaflex.domain.usecases.logservice;

import co.com.sofka.cuentaflex.domain.drivenports.messaging.Message;
import co.com.sofka.cuentaflex.domain.drivenports.messaging.MessageRepository;
import co.com.sofka.shared.domain.usecases.UseCase;

public class HandleMessageUseCase implements UseCase<Message, Void> {
    private final MessageRepository messageRepository;

    public HandleMessageUseCase(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Void execute(Message request) {
        messageRepository.saveMessage(request);
        return null;
    }
}
