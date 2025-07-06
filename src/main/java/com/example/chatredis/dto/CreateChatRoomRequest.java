package com.example.chatredis.dto;

public class CreateChatRoomRequest {
    private String roomName;

    public CreateChatRoomRequest() {}

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
} 