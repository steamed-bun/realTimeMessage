package com.realTimeMessage.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyHandler extends TextWebSocketHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyHandler.class);

    private static Map<String, WebSocketSession> SESSION_MAP = new HashMap<String, WebSocketSession>();


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        LOGGER.debug("[{} : {}] has be connected...", session.getUri(), session.getId());
        SESSION_MAP.put(session.getId(), session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        LOGGER.debug("[{} : {}]", session.getUri(), session.getId());
        SESSION_MAP.remove(session.getId());
    }

    public void broadcast(final TextMessage message) throws IOException {
        for (Map.Entry<String, WebSocketSession> entry : SESSION_MAP.entrySet()) {
            if (entry.getValue().isOpen()) {
                System.out.println("test");
            }
        }
    }

}
