package co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.endpoints.deposit.externalaccount;

import co.com.sofka.cuentaflex.domain.usecases.common.transactions.FeesValues;
import co.com.sofka.cuentaflex.domain.usecases.common.transactions.TransactionErrors;
import co.com.sofka.cuentaflex.domain.usecases.deposit.externalaccount.DepositToExternalAccountRequest;
import co.com.sofka.cuentaflex.domain.usecases.deposit.externalaccount.DepositToExternalAccountResponse;
import co.com.sofka.cuentaflex.domain.usecases.deposit.externalaccount.DepositToExternalAccountUseCase;
import co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.constants.AccountEndpointsConstants;
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
@RequestMapping(AccountEndpointsConstants.EXTERNAL_DEPOSIT_TO_ACCOUNT_URL)
@Tag(name = "Account Deposits")
public final class DepositToExternalAccountEndpoint {
    private final DepositToExternalAccountUseCase depositToExternalAccountUseCase;
    private final FeesValues feesValues;

    private final static Map<String, HttpStatus> ERROR_STATUS_MAP = new HashMap<>();

    static {
        ERROR_STATUS_MAP.put(TransactionErrors.ACCOUNT_NOT_FOUND.getCode(), HttpStatus.NOT_FOUND);
        ERROR_STATUS_MAP.put(TransactionErrors.INVALID_AMOUNT.getCode(), HttpStatus.BAD_REQUEST);
        ERROR_STATUS_MAP.put(TransactionErrors.INSUFFICIENT_FUNDS.getCode(), HttpStatus.BAD_REQUEST);
    }

    public DepositToExternalAccountEndpoint(DepositToExternalAccountUseCase depositToExternalAccountUseCase, FeesValues feesValues) {
        this.depositToExternalAccountUseCase = depositToExternalAccountUseCase;
        this.feesValues = feesValues;
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200"
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
    public ResponseEntity<DinResponse<DepositToExternalAccountResponseDto>> deposit(
            @RequestBody DinRequest<DepositToExternalAccountRequestDto> requestDto
    ) {
        DepositToExternalAccountRequest useCaseRequest = DepositToExternalAccountMapper.fromDinToUseCaseRequest(requestDto);
        ResultWith<DepositToExternalAccountResponse> useCaseResponse = this.depositToExternalAccountUseCase.execute(useCaseRequest);

        if (useCaseResponse.isFailure()) {
            HttpStatus status = ERROR_STATUS_MAP.getOrDefault(
                    useCaseResponse.getError().getCode(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );

            return ResponseEntity.status(status).body(
                    DinErrorMapper.fromUseCaseToDinResponse(
                            requestDto.getDinHeader(),
                            useCaseResponse.getError(),
                            getErrorDetails(requestDto, useCaseResponse)
                    )
            );
        }

        return ResponseEntity.ok(
                DepositToExternalAccountMapper.fromUseCaseToDinResponse(
                        requestDto.getDinHeader(),
                        useCaseResponse.getValue()
                )
        );
    }

    private String[] getErrorDetails(DinRequest<DepositToExternalAccountRequestDto> requestDto, ResultWith<DepositToExternalAccountResponse> result) {
        if(result.isSuccess()) {
            return new String[0];
        }

        if(result.getError().getCode().equals(TransactionErrors.INVALID_AMOUNT.getCode())) {
            return new String[] {this.feesValues.getDepositToExternalAccountFee().toString()};
        }

        if(result.getError().getCode().equals(TransactionErrors.ACCOUNT_NOT_FOUND.getCode())) {
            return new String[] {requestDto.getDinBody().getAccountId() + " or " + requestDto.getDinBody().getEncryptedAccountNumberToDeposit()};
        }

        return new String[0];
    }
}
