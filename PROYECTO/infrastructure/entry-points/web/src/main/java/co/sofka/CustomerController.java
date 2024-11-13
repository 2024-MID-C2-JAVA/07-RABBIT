package co.sofka;


import co.sofka.command.create.SaveCustumerHandler;
import co.sofka.command.dto.CustomerDTO;
import co.sofka.command.dto.request.CustomerSaveDTO;
import co.sofka.command.dto.request.RequestMs;
import co.sofka.command.dto.response.ResponseMs;
import co.sofka.command.query.ListAllCustomerHandler;
import co.sofka.data.ResponseAPI;
import co.sofka.usecase.IGetAllCustomerService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/client")
@AllArgsConstructor
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final ListAllCustomerHandler handler;

    private final SaveCustumerHandler saveCustumerHandler;


    @PostMapping("/all")
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
                                            value = "{\"dinHeader\":{\"dispositivo\":\"PC\",\"idioma\":\"es\",\"uuid\":\"02e3eb27-6fb1-e542-e157-c301cc77ad2c\",\"ip\":\"localhost\",\"horaTransaccion\":\"string\",\"llaveSimetrica\":\"xaqVyedHolrJB9vW4lIj5u9nuWIiaPpAQoOK4hm2j+Q=\",\"vectorInicializacion\":\"Gz3MLPvKU1T5Pc3FfmNYPe9nuWIiaPpAQoOK4hm2j+Q=\"},\"dinBody\":{\"id\":\"1\"}}",
                                            summary = "Full request")})})

    @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = ResponseMs.class)))
    public ResponseEntity<ResponseMs<List<CustomerDTO>>> getAll(

            @RequestBody RequestMs<Void> request
    ) {
        logger.info("Buscando todos los Customer");
        return new ResponseEntity<>( handler.getAll(request), HttpStatus.OK);
    }


    @PostMapping("/save")
    @ApiResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(implementation = ResponseMs.class)))
    public ResponseEntity<ResponseMs<CustomerDTO>> save(

            @RequestBody RequestMs<CustomerSaveDTO> request
    ) {
        logger.info("Save Customer");
        return new ResponseEntity<>( saveCustumerHandler.save(request), HttpStatus.OK);
    }



}
