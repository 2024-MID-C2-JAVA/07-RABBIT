package co.sofka.command;

import co.sofka.Customer;
import co.sofka.LogEvent;
import co.sofka.command.dto.request.RequestMs;
import co.sofka.command.dto.response.DinError;
import co.sofka.command.dto.response.ResponseMs;
import co.sofka.config.EncryptionAndDescryption;
import co.sofka.config.TokenByDinHeaders;
import co.sofka.gateway.ILogRepository;
import co.sofka.usecase.appBank.IGetAllCustomerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ListAllLogHandler {

    private static final Logger logger = LoggerFactory.getLogger(ListAllLogHandler.class);

    private final ILogRepository service;



    public ResponseMs<List<LogEvent>> getAll(RequestMs<Void> request) {

        ResponseMs<List<LogEvent>> responseMs = new ResponseMs<>();
        responseMs.setDinHeader(request.getDinHeader());
        DinError error = new DinError();

        List<LogEvent> list = service.list();


        responseMs.setDinBody(list);

        responseMs.setDinError(error);


        return responseMs;
    }
}
