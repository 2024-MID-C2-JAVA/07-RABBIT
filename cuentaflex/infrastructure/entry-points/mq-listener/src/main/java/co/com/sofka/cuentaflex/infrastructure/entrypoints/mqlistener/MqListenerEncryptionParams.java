package co.com.sofka.cuentaflex.infrastructure.entrypoints.mqlistener;

public final class MqListenerEncryptionParams {
    private final String symmetricKey;

    public MqListenerEncryptionParams(String symmetricKey) {
        this.symmetricKey = symmetricKey;
    }

    public String getSymmetricKey() {
        return symmetricKey;
    }
}
