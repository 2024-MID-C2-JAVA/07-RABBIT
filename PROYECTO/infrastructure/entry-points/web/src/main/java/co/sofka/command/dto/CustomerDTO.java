package co.sofka.command.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDTO {

    private String username;

    private String rol;

    private List<AccountDTO> accounts;


}
