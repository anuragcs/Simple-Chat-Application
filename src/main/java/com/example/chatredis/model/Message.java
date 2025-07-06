package com.example.chatredis.model;

import java.time.LocalDateTime;

public class Message {
    private String participant;
    private String message;
    private LocalDateTime timestamp;

    public Message() {}

    public Message(String participant, String message, LocalDateTime timestamp) {
        this.participant = participant;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
} 