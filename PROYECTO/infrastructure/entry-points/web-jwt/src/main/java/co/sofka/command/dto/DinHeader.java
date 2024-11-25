package co.sofka.command.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DinHeader {

    private String dispositivo;
    private String idioma;
    private String uuid;
    private String ip;
    private String horaTransaccion;
    private String llaveSimetrica;
    private String vectorInicializacion;
}
