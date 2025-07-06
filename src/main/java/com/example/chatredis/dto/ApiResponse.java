package com.example.chatredis.dto;

public class ApiResponse {
    private String message;
    private String status;
    private String roomId;

    public ApiResponse() {}

    public ApiResponse(String message, String status, String roomId) {
        this.message = message;
        this.status = status;
        this.roomId = roomId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
} 