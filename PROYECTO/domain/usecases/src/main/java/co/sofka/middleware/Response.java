package co.sofka.middleware;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

    private String message;
    /**
     * Code to response
     */
    private Integer code;

    private T bodyOut;

}
