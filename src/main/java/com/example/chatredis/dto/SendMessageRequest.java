package com.example.chatredis.dto;

public class SendMessageRequest {
    private String participant;
    private String message;

    public SendMessageRequest() {}

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
} 