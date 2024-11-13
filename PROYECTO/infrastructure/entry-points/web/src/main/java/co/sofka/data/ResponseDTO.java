package co.sofka.data;

public class ResponseDTO {

    public String id;

    public String customer;


    public ResponseDTO(String id, String customer) {
        this.id = id;
        this.customer = customer;
    }


    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
