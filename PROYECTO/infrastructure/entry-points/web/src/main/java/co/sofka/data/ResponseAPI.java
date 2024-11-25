package co.sofka.data;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseAPI<T> {

    private String message;
    /**
     * Code to response
     */
    private Integer code;

    private T bodyOut;

}
