package co.sofka;

import java.io.Serializable;

public class LogEvent {

    private Transaction message;

    private String id;

    private String type;

    private String fecha;

    public LogEvent() {
    }

    public Transaction getMessage() {
        return message;
    }

    public void setMessage(Transaction message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
