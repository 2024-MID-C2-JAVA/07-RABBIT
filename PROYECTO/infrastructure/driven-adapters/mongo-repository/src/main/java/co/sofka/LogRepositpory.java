package co.sofka;

import co.sofka.config.JpaLogRepository;
import co.sofka.data.entity.LogEntity;
import co.sofka.gateway.ILogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
@AllArgsConstructor
public class LogRepositpory implements ILogRepository {

    private final JpaLogRepository jpaLogRepository;

    @Override
    public void save(LogEvent log) {

        LogEntity logEntity = new LogEntity();
        logEntity.setMessage(log.getMessage().toString());
        logEntity.setType(log.getType());
        logEntity.setFecha(LocalDate.parse(log.getFecha()));

        jpaLogRepository.save(logEntity);
    }
}
