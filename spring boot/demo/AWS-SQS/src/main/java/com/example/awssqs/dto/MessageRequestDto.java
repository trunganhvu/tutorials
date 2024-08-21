package com.example.awssqs.dto;

import java.io.Serial;
import java.io.Serializable;

public class MessageRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 6033258192163387682L;

    private int id;
    private String message;

    public MessageRequestDto() {
    }

    public MessageRequestDto(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageRequestDto{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
