package co.sofka.data.commons;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private final Date timestamp;
    private final String code;
    private final String message;
}
