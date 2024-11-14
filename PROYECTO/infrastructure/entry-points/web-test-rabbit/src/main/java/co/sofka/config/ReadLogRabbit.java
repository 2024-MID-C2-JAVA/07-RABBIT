package co.sofka.config;

import co.sofka.LogEvent;
import co.sofka.usecase.appLog.ILogSaveService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
@Data
public class ReadLogRabbit {

   private final ILogSaveService iLogSaveService;

@RabbitListener(queues = "${general.config.rabbitmq.queue}")
public void readLog(LogEvent message) {
    System.out.println("Mensaje recibido: " + message);
    iLogSaveService.save(message);

}


}
