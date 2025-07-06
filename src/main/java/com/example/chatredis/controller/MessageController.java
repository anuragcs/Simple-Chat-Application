package com.example.chatredis.controller;

import com.example.chatredis.dto.ApiResponse;
import com.example.chatredis.dto.SendMessageRequest;
import com.example.chatredis.dto.ChatHistoryResponse;
import com.example.chatredis.service.ChatRoomService;
import com.example.chatredis.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chatapp/chatrooms/{roomId}/messages")
public class MessageController {
    private final MessageService messageService;
    private final ChatRoomService chatRoomService;

    @Autowired
    public MessageController(MessageService messageService, ChatRoomService chatRoomService) {
        this.messageService = messageService;
        this.chatRoomService = chatRoomService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> sendMessage(@PathVariable String roomId, @RequestBody SendMessageRequest request) {
        if (!chatRoomService.roomExists(roomId)) {
            return ResponseEntity.badRequest().body(new ApiResponse("Chat room does not exist.", "error", null));
        }
        messageService.sendMessage(roomId, request.getParticipant(), request.getMessage());
        return ResponseEntity.ok(new ApiResponse("Message sent successfully.", "success", null));
    }

    @GetMapping
    public ResponseEntity<ChatHistoryResponse> getChatHistory(@PathVariable String roomId, @RequestParam(defaultValue = "10") int limit) {
        if (!chatRoomService.roomExists(roomId)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(new ChatHistoryResponse(messageService.getLastNMessages(roomId, limit)));
    }
} 