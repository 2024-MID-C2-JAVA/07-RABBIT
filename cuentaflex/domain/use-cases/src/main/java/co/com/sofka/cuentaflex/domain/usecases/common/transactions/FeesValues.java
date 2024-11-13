package co.com.sofka.cuentaflex.domain.usecases.common.transactions;

import java.math.BigDecimal;

public final class FeesValues {
    private final BigDecimal depositFromBranchFee;
    private final BigDecimal depositFromAtmFee;
    private final BigDecimal depositToExternalAccountFee;
    private final BigDecimal purchaseOnlineFee;
    private final BigDecimal purchasePhysicallyFee;
    private final BigDecimal withdrawFromAtmFee;

    public FeesValues(BigDecimal depositFromBranchFee, BigDecimal depositFromAtmFee, BigDecimal depositToExternalAccountFee, BigDecimal purchaseOnlineFee, BigDecimal purchasePhysicallyFee, BigDecimal withdrawFromAtmFee) {
        this.depositFromBranchFee = depositFromBranchFee;
        this.depositFromAtmFee = depositFromAtmFee;
        this.depositToExternalAccountFee = depositToExternalAccountFee;
        this.purchaseOnlineFee = purchaseOnlineFee;
        this.purchasePhysicallyFee = purchasePhysicallyFee;
        this.withdrawFromAtmFee = withdrawFromAtmFee;
    }

    public BigDecimal getDepositFromBranchFee() {
        return depositFromBranchFee;
    }

    public BigDecimal getDepositFromAtmFee() {
        return depositFromAtmFee;
    }

    public BigDecimal getDepositToExternalAccountFee() {
        return depositToExternalAccountFee;
    }

    public BigDecimal getPurchaseOnlineFee() {
        return purchaseOnlineFee;
    }

    public BigDecimal getPurchasePhysicallyFee() {
        return purchasePhysicallyFee;
    }

    public BigDecimal getWithdrawFromAtmFee() {
        return withdrawFromAtmFee;
    }
}
