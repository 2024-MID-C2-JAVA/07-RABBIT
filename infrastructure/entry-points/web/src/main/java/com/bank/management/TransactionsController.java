package com.bank.management;

import com.bank.management.data.*;
import com.bank.management.enums.DinErrorCode;
import com.bank.management.exception.*;
import com.bank.management.usecase.appservice.EncryptionUseCase;
import com.bank.management.usecase.appservice.ProcessDepositUseCase;
import com.bank.management.usecase.appservice.ProcessPurchaseWithCardUseCase;
import com.bank.management.usecase.appservice.ProcessWithdrawUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/private/transactions")
public class TransactionsController {

    private final EncryptionUseCase encryptionUseCase;
    private final ProcessDepositUseCase processDepositUseCase;
    private final ProcessPurchaseWithCardUseCase processPurchaseWithCardUseCase;
    private final ProcessWithdrawUseCase processWithdrawUseCase;

    public TransactionsController(EncryptionUseCase encryptionUseCase, ProcessDepositUseCase processDepositUseCase, ProcessPurchaseWithCardUseCase processPurchaseWithCardUseCase, ProcessWithdrawUseCase processWithdrawUseCase) {
        this.encryptionUseCase = encryptionUseCase;
        this.processDepositUseCase = processDepositUseCase;
        this.processPurchaseWithCardUseCase = processPurchaseWithCardUseCase;
        this.processWithdrawUseCase = processWithdrawUseCase;
    }

    @PostMapping("/deposit")
    public ResponseEntity<ResponseMs<Map<String, String>>> processDeposit(@RequestBody @Valid RequestMs<RequestDepositDTO> request, Principal principal) {

        try {

            String authenticatedUsername = principal.getName();

            if (!authenticatedUsername.equals(request.getDinBody().getUsername())) {
                return ResponseBuilder.buildResponse(
                        request.getDinHeader(),
                        null,
                        DinErrorCode.ACCOUNT_DOESNT_BELONG,
                        HttpStatus.FORBIDDEN,
                        ""
                );
            }


            String encryptedAccountNumber = request.getDinBody().getAccountNumber();
            String symmetricKey = request.getDinHeader().getSymmetricKey();
            String initializationVector = request.getDinHeader().getInitializationVector();

            String decryptedAccountNumber = encryptionUseCase.decryptData(encryptedAccountNumber, symmetricKey, initializationVector);

            Deposit deposit = new Deposit.Builder()
                    .username(request.getDinBody().getUsername())
                    .accountNumber(decryptedAccountNumber)
                    .amount(request.getDinBody().getAmount())
                    .type(request.getDinBody().getType())
                    .build();

            Optional<Account> accountOptional = processDepositUseCase.apply(deposit);
            if (accountOptional.isEmpty()) {
                throw new BankAccountNotFoundException();
            }
            Account account = accountOptional.get();

            Map<String, String> responseData = new HashMap<>();
            String availableAmount = account.getAmount().toString();
            responseData.put("accountNumber", encryptedAccountNumber);
            responseData.put("amount", encryptionUseCase.encryptData(availableAmount, symmetricKey, initializationVector));

            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    responseData,
                    DinErrorCode.SUCCESS,
                    HttpStatus.OK,
                    "Deposit was successful."
            );

        } catch (InvalidAmountException | InvalidDepositTypeException e) {
            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    null,
                    DinErrorCode.DEPOSIT_FAILED,
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        } catch (BankAccountNotFoundException e) {
            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    null,
                    DinErrorCode.ACCOUNT_NOT_FOUND,
                    HttpStatus.NOT_FOUND,
                    e.getMessage()
            );
        } catch (CustomerNotFoundException e) {
            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    null,
                    DinErrorCode.CUSTOMER_NOT_FOUND,
                    HttpStatus.NOT_FOUND,
                    e.getMessage()
            );
        } catch(IllegalArgumentException e) {
            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    null,
                    DinErrorCode.BAD_REQUEST,
                    HttpStatus.BAD_REQUEST,
                    ""
            );
        } catch (Exception e) {
            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    null,
                    DinErrorCode.DEPOSIT_FAILED,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
        }
    }

    @PostMapping("/purchase-card")
    public ResponseEntity<ResponseMs<Map<String, String>>> processPurchase(@RequestBody @Valid RequestMs<RequestPurchaseDTO> request) {

        try {
            String encryptedAccountNumber = request.getDinBody().getAccountNumber();
            String symmetricKey = request.getDinHeader().getSymmetricKey();
            String initializationVector = request.getDinHeader().getInitializationVector();

            String decryptedAccountNumber = encryptionUseCase.decryptData(encryptedAccountNumber, symmetricKey, initializationVector);

            Purchase purchase = new Purchase.Builder()
                    .accountNumber(decryptedAccountNumber)
                    .amount(request.getDinBody().getAmount())
                    .type(request.getDinBody().getType())
                    .build();

            Optional<Account> accountOptional = processPurchaseWithCardUseCase.apply(purchase);

            if (accountOptional.isEmpty()) {
                throw new BankAccountNotFoundException();
            }

            Map<String, String> responseData = new HashMap<>();
            String availableAmount = accountOptional.get().getAmount().toString();
            responseData.put("accountNumber", encryptedAccountNumber);
            responseData.put("amount", encryptionUseCase.encryptData(availableAmount, symmetricKey, initializationVector));

            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    responseData,
                    DinErrorCode.SUCCESS,
                    HttpStatus.OK,
                    "Purchase was successful."
            );

        } catch (BankAccountNotFoundException e) {
            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    null,
                    DinErrorCode.ACCOUNT_NOT_FOUND,
                    HttpStatus.NOT_FOUND,
                    e.getMessage()
            );
        } catch (CustomerNotFoundException e) {
            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    null,
                    DinErrorCode.CUSTOMER_NOT_FOUND,
                    HttpStatus.NOT_FOUND,
                    e.getMessage()
            );
        } catch (InvalidPurchaseTypeException | InsufficientFundsException e) {
            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    null,
                    DinErrorCode.PURCHASE_FAILED,
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        } catch(IllegalArgumentException e) {
            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    null,
                    DinErrorCode.BAD_REQUEST,
                    HttpStatus.BAD_REQUEST,
                    ""
            );
        } catch (Exception e) {
            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    null,
                    DinErrorCode.PURCHASE_FAILED,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ResponseMs<Map<String, String>>> processWithdraw(@RequestBody @Valid RequestMs<RequestWithdrawalDTO> request, Principal principal) {

        try {

            String authenticatedUsername = principal.getName();

            if (!authenticatedUsername.equals(request.getDinBody().getUsername())) {
                return ResponseBuilder.buildResponse(
                        request.getDinHeader(),
                        null,
                        DinErrorCode.ACCOUNT_DOESNT_BELONG,
                        HttpStatus.FORBIDDEN,
                        ""
                );
            }
            
            String encryptedAccountNumber = request.getDinBody().getAccountNumber();
            String symmetricKey = request.getDinHeader().getSymmetricKey();
            String initializationVector = request.getDinHeader().getInitializationVector();

            String decryptedAccountNumber = encryptionUseCase.decryptData(encryptedAccountNumber, symmetricKey, initializationVector);

            Withdrawal withdrawal = new Withdrawal.Builder()
                    .setUsername(request.getDinBody().getUsername())
                    .setAccountNumber(decryptedAccountNumber)
                    .setAmount(request.getDinBody().getAmount())
                    .build();

            Optional<Account> accountOptional = processWithdrawUseCase.apply(withdrawal);

            if (accountOptional.isEmpty()) {
                throw new BankAccountNotFoundException();
            }

            Map<String, String> responseData = new HashMap<>();
            String availableAmount = accountOptional.get().getAmount().toString();
            responseData.put("accountNumber", encryptedAccountNumber);
            responseData.put("amount", encryptionUseCase.encryptData(availableAmount, symmetricKey, initializationVector));

            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    responseData,
                    DinErrorCode.SUCCESS,
                    HttpStatus.OK,
                    "Withdrawal was successful."
            );

        } catch (InvalidAmountException | InsufficientFundsException e) {
            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    null,
                    DinErrorCode.WITHDRAWAL_FAILED,
                    HttpStatus.BAD_REQUEST,
                    e.getMessage()
            );
        } catch (BankAccountNotFoundException e) {
            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    null,
                    DinErrorCode.ACCOUNT_NOT_FOUND,
                    HttpStatus.NOT_FOUND,
                    e.getMessage()
            );
        } catch (CustomerNotFoundException e) {
            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    null,
                    DinErrorCode.CUSTOMER_NOT_FOUND,
                    HttpStatus.NOT_FOUND,
                    e.getMessage()
            );
        } catch(IllegalArgumentException e) {
            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    null,
                    DinErrorCode.BAD_REQUEST,
                    HttpStatus.BAD_REQUEST,
                    ""
            );
        } catch (Exception e) {
            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    null,
                    DinErrorCode.WITHDRAWAL_FAILED,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
        }
    }
}
