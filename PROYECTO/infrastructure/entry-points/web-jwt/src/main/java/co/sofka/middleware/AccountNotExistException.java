package co.sofka.middleware;

import co.sofka.command.dto.DinHeader;
import lombok.Data;

@Data
public class AccountNotExistException extends RuntimeException{

    private final DinHeader dinHeader;

    private final int code;

    public AccountNotExistException(String message, DinHeader dinHeader, int code) {
        super(message);
        this.dinHeader = dinHeader;
        this.code = code;
    }
}
