package com.example.chatredis.dto;

import com.example.chatredis.model.Message;
import java.util.List;

public class ChatHistoryResponse {
    private List<Message> messages;

    public ChatHistoryResponse() {}

    public ChatHistoryResponse(List<Message> messages) {
        this.messages = messages;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
} 