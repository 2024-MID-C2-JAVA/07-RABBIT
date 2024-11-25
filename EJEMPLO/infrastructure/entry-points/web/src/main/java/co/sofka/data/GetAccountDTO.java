package co.sofka.data;

public class GetAccountDTO {

    private String id;

    public GetAccountDTO() {
    }

    public GetAccountDTO(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
