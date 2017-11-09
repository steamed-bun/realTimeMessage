package com.realTimeMessage.socket;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;
import org.springframework.messaging.support.MessageHeaderAccessor;

import java.util.HashMap;
import java.util.Map;

public class MyChannelInterceptor extends ChannelInterceptorAdapter {

    private static Map<String, String> pool =  new HashMap<String, String>(); // 连接池

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        StompCommand stompCommand = accessor.getCommand();
        String simpSessionId = accessor.getHeader("simpSessionId").toString();
        if (StompCommand.CONNECT.equals(stompCommand)) {
            String userId = accessor.getFirstNativeHeader("userId");
            System.out.println(userId + "上线，当前在线人数：" + pool.size());
            if (userId != null){
                pool.put(userId, simpSessionId);
            }
        } else if (StompCommand.DISCONNECT.equals(stompCommand)) {
            String userId = pool.get(simpSessionId);
            if (userId != null) {
                pool.remove(simpSessionId);
                System.out.println(userId + "离线，当前在线人数：" + pool.size());
            }
        }
        return message;
    }

    public static Map<String, String> getPool() {
        return pool;
    }
}
