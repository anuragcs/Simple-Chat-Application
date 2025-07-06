package com.example.chatredis.service;

import com.example.chatredis.model.ChatRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class ChatRoomService {
    private static final String CHATROOM_HASH = "chatrooms";
    private static final String PARTICIPANTS_PREFIX = "chatroom:participants:";

    private final RedisTemplate<String, Object> redisTemplate;
    private final HashOperations<String, String, ChatRoom> hashOps;
    private final SetOperations<String, Object> setOps;

    @Autowired
    public ChatRoomService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOps = redisTemplate.opsForHash();
        this.setOps = redisTemplate.opsForSet();
    }

    public boolean roomExists(String roomId) {
        return hashOps.hasKey(CHATROOM_HASH, roomId);
    }

    public ChatRoom createRoom(String roomId, String roomName) {
        if (roomExists(roomId)) {
            throw new IllegalArgumentException("Chat room with this name already exists.");
        }
        ChatRoom chatRoom = new ChatRoom(roomId, roomName, LocalDateTime.now());
        hashOps.put(CHATROOM_HASH, roomId, chatRoom);
        return chatRoom;
    }

    public void addParticipant(String roomId, String participant) {
        if (!roomExists(roomId)) {
            throw new IllegalArgumentException("Chat room does not exist.");
        }
        setOps.add(PARTICIPANTS_PREFIX + roomId, participant);
    }

    public ChatRoom getRoom(String roomId) {
        return hashOps.get(CHATROOM_HASH, roomId);
    }

    public Map<String, ChatRoom> getAllRooms() {
        return hashOps.entries(CHATROOM_HASH);
    }
} 