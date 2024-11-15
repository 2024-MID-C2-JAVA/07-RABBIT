package co.sofka;


import co.sofka.command.ListAllLogHandler;
import co.sofka.command.dto.request.RequestMs;
import co.sofka.command.dto.response.ResponseMs;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/log")
@AllArgsConstructor
public class LogController {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);

    private final ListAllLogHandler handler;


    @PostMapping("/all")
    public ResponseEntity<ResponseMs<List<LogEvent>>> getAll(

            @RequestBody RequestMs<Void> request
    ) {
        logger.info("Buscando todos los Log");
        return new ResponseEntity<>( handler.getAll(request), HttpStatus.OK);
    }



}
