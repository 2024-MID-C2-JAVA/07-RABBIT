package co.sofka;

import co.sofka.config.JpaLogRepository;
import co.sofka.data.entity.LogEntity;
import co.sofka.gateway.ILogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<LogEvent> list() {
        List<LogEntity> all = jpaLogRepository.findAll();

        List<LogEvent> logs = new ArrayList<>();

        all.forEach(logEntity -> {
            LogEvent log = new LogEvent();

            log.setMessage(logEntity.getMessage());
            log.setType(logEntity.getType());
            log.setFecha(logEntity.getFecha().toString());
            logs.add(log);
        });

        return logs;
    }
}
