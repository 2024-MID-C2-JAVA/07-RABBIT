package co.com.sofka.cuentaflex.domain.usecases.utils.transactions;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class TransactionResponse {
    private String payrollAccountNumber;
    private String supplierAccountNumber;
    private BigDecimal amount;
    private BigDecimal cost;
    private String type;
    private LocalDateTime timestamp;
}
