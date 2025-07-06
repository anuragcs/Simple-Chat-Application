package com.example.chatredis.model;

import java.time.LocalDateTime;

public class ChatRoom {
    private String roomId;
    private String roomName;
    private LocalDateTime createdAt;

    public ChatRoom() {}

    public ChatRoom(String roomId, String roomName, LocalDateTime createdAt) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.createdAt = createdAt;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
} 