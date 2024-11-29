package co.com.sofka.shared.infrastructure.entrypoints.din;

public class DinResponse<BodyType> {
    private DinHeader dinHeader;
    private BodyType dinBody;
    private DinError dinError;

    public DinResponse() {
    }

    public DinResponse(DinHeader dinHeader, BodyType dinBody) {
        this.dinHeader = dinHeader;
        this.dinBody = dinBody;
        this.dinError = DinError.getDefaultDinError();
    }

    public DinResponse(DinHeader dinHeader, DinError dinError) {
        this.dinHeader = dinHeader;
        this.dinBody = null;
        this.dinError = dinError;
    }

    public DinHeader getDinHeader() {
        return dinHeader;
    }

    public void setDinHeader(DinHeader dinHeader) {
        this.dinHeader = dinHeader;
    }

    public BodyType getDinBody() {
        return dinBody;
    }

    public void setDinBody(BodyType dinBody) {
        this.dinBody = dinBody;
    }

    public DinError getDinError() {
        return dinError;
    }

    public void setDinError(DinError dinError) {
        this.dinError = dinError;
    }
}
