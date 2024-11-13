package co.sofka.command.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountDTO {

    private String number;

    private BigDecimal amount;


}
