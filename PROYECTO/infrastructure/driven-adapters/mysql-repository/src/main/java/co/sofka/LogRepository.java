package co.sofka;



import co.sofka.config.JpaCustomerRepository;
import co.sofka.config.JpaLogRepository;
import co.sofka.data.entity.CustomerEntity;
import co.sofka.data.entity.LogEntity;
import co.sofka.gateway.ICustomerRepository;
import co.sofka.gateway.ILogRepository;
import co.sofka.mapper.CustomerMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class LogRepository implements ILogRepository {

    private static final Logger logger = LoggerFactory.getLogger(LogRepository.class);

    private final JpaLogRepository repository;



    @Override
    public void save(LogEvent log) {

        LogEntity logEntity = new LogEntity();
        logEntity.setFecha(LocalDate.now());
        logEntity.setType(log.getType());
        logEntity.setMessage(log.getMessage().toString());

        repository.save(logEntity);
    }
}
