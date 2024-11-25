package co.sofka.command.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DinError {

    private String tipo= "SUCCESS";
    private String fecha= LocalDateTime.now().toString();
    private String origen;
    private String codigo= "0";
    private String codigoErrorProveedor= "0000";
    private String mensaje= "Operacion exitosa";
    private String detalle ;
}
