package co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.endpoints.customer.createaccount;

import co.com.sofka.cuentaflex.domain.usecases.customer.createaccount.CreateCustomerAccountErrors;
import co.com.sofka.cuentaflex.domain.usecases.customer.createaccount.CreateCustomerAccountRequest;
import co.com.sofka.cuentaflex.domain.usecases.customer.createaccount.CreateCustomerAccountResponse;
import co.com.sofka.cuentaflex.domain.usecases.customer.createaccount.CreateCustomerAccountUseCase;
import co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.constants.CustomerEndpointsConstants;
import co.com.sofka.shared.domain.usecases.ResultWith;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinErrorMapper;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinRequest;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(CustomerEndpointsConstants.CREATE_CUSTOMER_ACCOUNT_ENDPOINT)
@Tag(name = "Customer Account")
public final class CreateCustomerAccountEndpoint {
    private final CreateCustomerAccountUseCase createCustomerAccountUseCase;

    private static final Map<String, HttpStatus> ERROR_STATUS_MAP = new HashMap<>();

    static {
        ERROR_STATUS_MAP.put(CreateCustomerAccountErrors.CUSTOMER_NOT_FOUND.getCode(), HttpStatus.NOT_FOUND);
        ERROR_STATUS_MAP.put(CreateCustomerAccountErrors.NEGATIVE_INITIAL_AMOUNT.getCode(), HttpStatus.BAD_REQUEST);
    }

    public CreateCustomerAccountEndpoint(CreateCustomerAccountUseCase createCustomerAccountUseCase) {
        this.createCustomerAccountUseCase = createCustomerAccountUseCase;
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "201"
            ),
            @ApiResponse(
                    responseCode = "400",
                    content = @Content(schema = @Schema(implementation = DinResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(schema = @Schema(implementation = DinResponse.class))
            )
    })

    @PostMapping
    public ResponseEntity<DinResponse<CreateCustomerAccountResponseDto>> createCustomerAccountResponse(
            @RequestBody DinRequest<CreateCustomerAccountRequestDto> createCustomerAccountRequestDto
    ) {
        CreateCustomerAccountRequest useCaseRequest = CreateCustomerAccountMapper.fromDinToUseCaseRequest(createCustomerAccountRequestDto);
        ResultWith<CreateCustomerAccountResponse> useCaseResponse = createCustomerAccountUseCase.execute(useCaseRequest);
        if (useCaseResponse.isFailure()) {
            HttpStatus status = ERROR_STATUS_MAP.getOrDefault(
                    useCaseResponse.getError().getCode(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );

            return ResponseEntity.status(status).body(
                    DinErrorMapper.fromUseCaseToDinResponse(
                            createCustomerAccountRequestDto.getDinHeader(),
                            useCaseResponse.getError(),
                            createCustomerAccountRequestDto.getDinBody().getCustomerId()
                    )
            );
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        CreateCustomerAccountMapper.fromUseCaseToDinResponse(
                                createCustomerAccountRequestDto.getDinHeader(),
                                useCaseResponse.getValue()
                        )
                );
    }
}
