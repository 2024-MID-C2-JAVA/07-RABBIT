package com.bank.management;

import com.bank.management.data.*;
import com.bank.management.enums.DinErrorCode;
import com.bank.management.exception.AccountCreationException;
import com.bank.management.exception.BankAccountNotFoundException;
import com.bank.management.exception.CustomerNotFoundException;
import com.bank.management.usecase.appservice.CreateBankAccountUseCase;
import com.bank.management.usecase.appservice.DeleteBankAccountUseCase;
import com.bank.management.usecase.appservice.GetAccountsByCustomerUseCase;
import com.bank.management.usecase.appservice.GetBankAccountUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/public/bank-accounts")
public class AccountController {

    private final CreateBankAccountUseCase createBankAccountUseCase;
    private final GetAccountsByCustomerUseCase getAccountsByCustomerUseCase;
    private final GetBankAccountUseCase getBankAccountUseCase;
    private final DeleteBankAccountUseCase deleteBankAccountUseCase;

    public AccountController(CreateBankAccountUseCase createBankAccountUseCase, GetAccountsByCustomerUseCase getAccountsByCustomerUseCase, GetBankAccountUseCase getBankAccountUseCase, DeleteBankAccountUseCase deleteBankAccountUseCase) {
        this.createBankAccountUseCase = createBankAccountUseCase;
        this.getAccountsByCustomerUseCase = getAccountsByCustomerUseCase;
        this.getBankAccountUseCase = getBankAccountUseCase;
        this.deleteBankAccountUseCase = deleteBankAccountUseCase;
    }

    @PostMapping
    public ResponseEntity<ResponseMs<Map<String, String>>> createAccount(@RequestBody @Valid RequestMs<RequestCreateAccountDTO> request) {

        try {

            Account accountDomain = new Account.Builder().amount(request.getDinBody().getAmount()).build();
            Customer customerDomain = new Customer.Builder().id(request.getDinBody().getCustomerId()).build();

            Account accountCreated = createBankAccountUseCase.apply(accountDomain, customerDomain);

            Map<String, String> responseData = new HashMap<>();
            responseData.put("accountNumber", accountCreated.getNumber());

            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    responseData,
                    DinErrorCode.SUCCESS,
                    HttpStatus.CREATED,
                    "Account creation process completed."
            );

        } catch (CustomerNotFoundException e) {
            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    null,
                    DinErrorCode.ERROR_CREATING_ACCOUNT,
                    HttpStatus.NOT_FOUND,
                    e.getMessage()
            );

        } catch (AccountCreationException e) {
            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    null,
                    DinErrorCode.ERROR_CREATING_ACCOUNT,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );

        }catch(IllegalArgumentException e) {
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
                    DinErrorCode.UNKNOWN_ERROR,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
        }
    }

    @PostMapping("/customer/get-accounts")
    public ResponseEntity<ResponseMs<List<BankAccountDTO>>> getBankAccountByCustomer(@RequestBody @Valid RequestMs<RequestGetBankAccountDTO> request) {

        try {

            List<Account> accounts = getAccountsByCustomerUseCase.apply(request.getDinBody().getId());

            List<BankAccountDTO> accountDTOs = accounts.stream()
                    .map(account -> new BankAccountDTO.Builder()
                            .number(account.getNumber())
                            .amount(account.getAmount())
                            .build())
                    .collect(Collectors.toList());

            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    accountDTOs,
                    DinErrorCode.SUCCESS,
                    HttpStatus.CREATED,
                    "Account creation process completed."
            );
        }catch(IllegalArgumentException e) {
            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    null,
                    DinErrorCode.BAD_REQUEST,
                    HttpStatus.BAD_REQUEST,
                    ""
            );
        }
    }

    @PostMapping("/get")
    public ResponseEntity<ResponseMs<BankAccountDTO>> getBankAccount(@RequestBody @Valid RequestMs<RequestGetBankAccountDTO> request) {

        try {
            Account account = getBankAccountUseCase.apply(request.getDinBody().getId());

            BankAccountDTO accountDTO = new BankAccountDTO.Builder()
                    .number(account.getNumber())
                    .amount(account.getAmount())
                    .build();

            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    accountDTO,
                    DinErrorCode.SUCCESS,
                    HttpStatus.OK,
                    "Account information was retrieved successfully."
            );

        }  catch (BankAccountNotFoundException e) {
            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    null,
                    DinErrorCode.ACCOUNT_NOT_FOUND,
                    HttpStatus.NOT_FOUND,
                    "No account found with the provided ID."
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
                    DinErrorCode.ERROR_DELETING_ACCOUNT,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "An unexpected error occurred while deleting the bank account."
            );
        }

    }

    @PostMapping("/delete")
    public ResponseEntity<ResponseMs<Map<String, String>>> deleteBankAccount(@RequestBody @Valid RequestMs<RequestGetBankAccountDTO> request) {

        try {
            boolean isDeleted = deleteBankAccountUseCase.apply(request.getDinBody().getId());
            Map<String, String> responseData = new HashMap<>();
            responseData.put("accountNumber", request.getDinBody().getId());

            if (isDeleted) {
                return ResponseBuilder.buildResponse(
                        request.getDinHeader(),
                        responseData,
                        DinErrorCode.SUCCESS,
                        HttpStatus.OK,
                        "Bank account deleted successfully."
                );
            } else {
                return ResponseBuilder.buildResponse(
                        request.getDinHeader(),
                        responseData,
                        DinErrorCode.ERROR_DELETING_ACCOUNT,
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Failed to delete the bank account due to a system error."
                );
            }

        } catch (BankAccountNotFoundException e) {
            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    null,
                    DinErrorCode.ACCOUNT_NOT_FOUND,
                    HttpStatus.NOT_FOUND,
                    "No bank account found with the provided ID."
            );
        }catch(IllegalArgumentException e) {
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
                    DinErrorCode.ERROR_DELETING_ACCOUNT,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "An unexpected error occurred while deleting the bank account."
            );
        }
    }
}
