package co.sofka.security.configuration.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginPartnerRequest {

    @NotBlank(message = "El campo número de identificación es requerido.")
    private String username;

    @NotBlank(message = "El campo tipo de identificación es requerido.")
    private String rol;

    @NotBlank(message = "El campo identificación dispositivo es requerido.")
    private List<String> permisos;

}
