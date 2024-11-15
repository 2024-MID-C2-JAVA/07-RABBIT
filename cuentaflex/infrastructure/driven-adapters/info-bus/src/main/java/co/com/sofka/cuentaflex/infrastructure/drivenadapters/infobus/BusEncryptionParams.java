package co.com.sofka.cuentaflex.infrastructure.drivenadapters.infobus;

public final class BusEncryptionParams {
    private final String symmetricKey;

    public BusEncryptionParams(String symmetricKey) {
        this.symmetricKey = symmetricKey;
    }

    public String getSymmetricKey() {
        return symmetricKey;
    }
}
