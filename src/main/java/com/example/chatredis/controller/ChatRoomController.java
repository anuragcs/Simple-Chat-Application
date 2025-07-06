package com.example.chatredis.controller;

import com.example.chatredis.dto.ApiResponse;
import com.example.chatredis.dto.CreateChatRoomRequest;
import com.example.chatredis.dto.JoinChatRoomRequest;
import com.example.chatredis.model.ChatRoom;
import com.example.chatredis.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chatapp/chatrooms")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createChatRoom(@RequestBody CreateChatRoomRequest request) {
        String roomId = request.getRoomName(); // Use roomName as roomId for simplicity
        try {
            ChatRoom chatRoom = chatRoomService.createRoom(roomId, request.getRoomName());
            return ResponseEntity.ok(new ApiResponse(
                    "Chat room '" + chatRoom.getRoomName() + "' created successfully.",
                    "success",
                    chatRoom.getRoomId()
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), "error", null));
        }
    }

    @PostMapping("/{roomId}/join")
    public ResponseEntity<ApiResponse> joinChatRoom(@PathVariable String roomId, @RequestBody JoinChatRoomRequest request) {
        try {
            chatRoomService.addParticipant(roomId, request.getParticipant());
            return ResponseEntity.ok(new ApiResponse(
                    "User '" + request.getParticipant() + "' joined chat room '" + roomId + "'.",
                    "success",
                    null
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), "error", null));
        }
    }
} 