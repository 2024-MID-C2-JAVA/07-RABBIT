package co.sofka.data.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "log")
public class LogEntity {

    @Id
    private String id;

    private String message;

    private String type;

    @Field(name = "fecha")
    private LocalDate fecha;






}
