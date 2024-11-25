package co.sofka;

import co.sofka.data.GetAccountDTO;
import co.sofka.data.ResponseDTO;
import co.sofka.usecase.GetAccountByIdUseCase;
import org.springframework.stereotype.Component;

@Component
public class Handler {

    private final GetAccountByIdUseCase getAccountByIdUseCase;

    private Handler(GetAccountByIdUseCase getAccountByIdUseCase) {
        this.getAccountByIdUseCase = getAccountByIdUseCase;
    }

    public ResponseDTO getAccountById(GetAccountDTO dto) {

        Account account = getAccountByIdUseCase.apply(dto.getId());

        ResponseDTO responseDTO = new ResponseDTO(
                account.getId(),
                account.getCustomer()
        );

        return responseDTO;
    }
}
