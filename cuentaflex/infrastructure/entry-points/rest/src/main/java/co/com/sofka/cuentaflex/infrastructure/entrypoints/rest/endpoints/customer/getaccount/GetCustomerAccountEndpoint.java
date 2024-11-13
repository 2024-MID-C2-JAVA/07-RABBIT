package co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.endpoints.customer.getaccount;

import co.com.sofka.cuentaflex.domain.usecases.customer.getaccount.GetCustomerAccountErrors;
import co.com.sofka.cuentaflex.domain.usecases.customer.getaccount.GetCustomerAccountRequest;
import co.com.sofka.cuentaflex.domain.usecases.customer.getaccount.GetCustomerAccountResponse;
import co.com.sofka.cuentaflex.domain.usecases.customer.getaccount.GetCustomerAccountUseCase;
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
@RequestMapping(CustomerEndpointsConstants.GET_CUSTOMER_ACCOUNT)
@Tag(name = "Customer Account")
public final class GetCustomerAccountEndpoint {
    private final GetCustomerAccountUseCase getCustomerAccountUseCase;

    private static final Map<String, HttpStatus> ERROR_STATUS_MAP = new HashMap<>();

    static {
        ERROR_STATUS_MAP.put(GetCustomerAccountErrors.ACCOUNT_NOT_FOUND.getCode(), HttpStatus.NOT_FOUND);
    }

    public GetCustomerAccountEndpoint(GetCustomerAccountUseCase getCustomerAccountUseCase) {
        this.getCustomerAccountUseCase = getCustomerAccountUseCase;
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200"
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = @Content(schema = @Schema(implementation = DinResponse.class))
            )
    })
    @GetMapping
    public ResponseEntity<DinResponse<GetCustomerAccountResponseDto>> getCustomerAccount(
            @RequestBody DinRequest<GetCustomerAccountRequestDto> request
    ) {
        GetCustomerAccountRequest useCaseRequest = GetCustomerAccountMapper.fromDinToUseCaseRequest(request);
        ResultWith<GetCustomerAccountResponse> useCaseResponse = this.getCustomerAccountUseCase.execute(useCaseRequest);

        if(useCaseResponse.isFailure()) {
            HttpStatus status = ERROR_STATUS_MAP.getOrDefault(
                    useCaseResponse.getError().getCode(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );

            return ResponseEntity.status(status)
                    .body(DinErrorMapper.fromUseCaseToDinResponse(
                            request.getDinHeader(), useCaseResponse.getError(),
                            request.getDinBody().getAccountId()
                    ));
        }

        return ResponseEntity.ok(GetCustomerAccountMapper.fromUseCaseToDinResponse(
                request.getDinHeader(),
                useCaseResponse.getValue())
        );
    }
}
