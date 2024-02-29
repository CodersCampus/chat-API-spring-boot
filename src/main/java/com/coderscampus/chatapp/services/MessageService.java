package com.coderscampus.chatapp.services;

import com.coderscampus.chatapp.domain.Message;
import com.coderscampus.chatapp.repository.MessageRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> findBySenderOrRecipient(String senderId, String recipientId) {
     return  messageRepository.findMessagesBetweenUsers(senderId ,recipientId);


//    return messageRepository.findAll();
    }

    public Message saveMessage(Message message) {
        Message newMessage = new Message();
        newMessage.setMessage(message.getMessage());
        newMessage.setCreatedAt(message.getCreatedAt());
        newMessage.setUniqueId(message.getUniqueId());
        newMessage.setSender(message.getSender());
        newMessage.setRecipient(message.getRecipient());
        return messageRepository.save(newMessage);

    }
}
