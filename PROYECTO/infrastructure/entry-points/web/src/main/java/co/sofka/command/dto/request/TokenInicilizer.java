package co.sofka.command.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenInicilizer {
    private String username;
    private String password;
}
