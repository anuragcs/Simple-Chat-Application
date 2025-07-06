package com.example.chatredis.dto;

public class JoinChatRoomRequest {
    private String participant;

    public JoinChatRoomRequest() {}

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }
} 