package com.coderscampus.chatapp.config.ws;

import com.coderscampus.chatapp.domain.Message;
import com.coderscampus.chatapp.services.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    private ObjectMapper mapper = new ObjectMapper();


    private final MessageService messageService;




@Autowired
    public ChatWebSocketHandler(MessageService messageService){

        this.messageService = messageService;
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("New WebSocket connection established with session ID: " + session.getId());
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Message chatMessage = mapper.readValue(message.getPayload(), Message.class);
        System.out.println(chatMessage.getMessage());
        for(WebSocketSession webSocketSession : sessions) {
            System.out.println(webSocketSession);
            if(webSocketSession.isOpen()) {
                if (messageService != null) {
                    System.out.println("Sending message back...");
                    Message savedMessage = messageService.saveMessage(chatMessage);
                    System.out.println("Saved message: " + savedMessage);
                    webSocketSession.sendMessage(new TextMessage(mapper.writeValueAsString(chatMessage)));
                }

            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("WebSocket connection was closed");
        sessions.remove(session);
    }
}
