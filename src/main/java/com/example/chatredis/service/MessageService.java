package com.example.chatredis.service;

import com.example.chatredis.model.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private static final String MESSAGES_PREFIX = "chatroom:messages:";
    private static final String PUBSUB_PREFIX = "chatroom:pubsub:";

    private final RedisTemplate<String, Object> redisTemplate;
    private final ListOperations<String, Object> listOps;
    private final StringRedisTemplate stringRedisTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public MessageService(RedisTemplate<String, Object> redisTemplate, StringRedisTemplate stringRedisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.listOps = redisTemplate.opsForList();
        this.stringRedisTemplate = stringRedisTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(String roomId, String participant, String messageText) {
        Message message = new Message(participant, messageText, LocalDateTime.now());
        try {
            String json = objectMapper.writeValueAsString(message);
            listOps.rightPush(MESSAGES_PREFIX + roomId, json);
            // Publish to Pub/Sub for real-time updates
            stringRedisTemplate.convertAndSend(PUBSUB_PREFIX + roomId, json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize message", e);
        }
    }

    public List<Message> getLastNMessages(String roomId, int n) {
        long size = listOps.size(MESSAGES_PREFIX + roomId);
        long start = Math.max(0, size - n);
        List<Object> jsonList = listOps.range(MESSAGES_PREFIX + roomId, start, size - 1);
        List<Message> messages = new ArrayList<>();
        if (jsonList != null) {
            for (Object obj : jsonList) {
                try {
                    messages.add(objectMapper.readValue(obj.toString(), Message.class));
                } catch (JsonProcessingException e) {
                    // skip malformed messages
                }
            }
        }
        return messages;
    }
} 