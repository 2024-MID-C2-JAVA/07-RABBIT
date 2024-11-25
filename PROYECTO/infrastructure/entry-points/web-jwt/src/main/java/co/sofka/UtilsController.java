package co.sofka;


import co.sofka.command.create.TokenGenerateHandler;
import co.sofka.command.dto.request.RequestMs;
import co.sofka.command.dto.request.TokenInicilizer;
import co.sofka.command.dto.response.ResponseMs;
import co.sofka.command.dto.response.TokenResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/utils")
@AllArgsConstructor
public class UtilsController {

    private static final Logger logger = LoggerFactory.getLogger(UtilsController.class);

    private final TokenGenerateHandler handler;




    @PostMapping("/generate")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Request example",
            required = true,
            content = {
                    @Content(
                            schema = @Schema(implementation = RequestMs.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            name = "Ejemplo JSON",
                                            value = "{\"dinHeader\":{\"dispositivo\":\"PC\",\"idioma\":\"es\",\"uuid\":\"02e3eb27-6fb1-e542-e157-c301cc77ad2c\",\"ip\":\"localhost\",\"horaTransaccion\":\"string\",\"llaveSimetrica\":\"xaqVyedHolrJB9vW4lIj5u9nuWIiaPpAQoOK4hm2j+Q=\",\"vectorInicializacion\":\"Gz3MLPvKU1T5Pc3FfmNYPe9nuWIiaPpAQoOK4hm2j+Q=\"},\"dinBody\":{\"username\":\"pablo\",\"password\":\"12qwaszx\"}}",
                                            summary = "Full request")})})
    @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = ResponseMs.class)))
    public ResponseEntity<ResponseMs<TokenResponse>> generateToken(@RequestBody RequestMs<TokenInicilizer> request) {
        logger.info("Buscando todos los Customer");
        return new ResponseEntity<>( handler.apply(request), HttpStatus.OK);
    }



}
