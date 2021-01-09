package se.iths.autofixjms.jms.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public class MessageObject implements Serializable {

    private UUID id;
    private String message;
    private String email;
    private String firstname;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;


    public MessageObject(UUID id, String message, LocalDateTime localDateTime,String email,String firstname) {
        this.id = id;
        this.message = message;
        this.localDateTime = localDateTime;
        this.email=email;
        this.firstname=firstname;
    }

    public MessageObject() {
    }

    public UUID getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    @Override
    public String toString() {
        return "MessageObject{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
