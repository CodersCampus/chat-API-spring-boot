package com.coderscampus.chatapp.controllers;


import com.coderscampus.chatapp.domain.Message;
import com.coderscampus.chatapp.dto.UserInformation;
import com.coderscampus.chatapp.repository.MessageRepository;
import com.coderscampus.chatapp.services.MessageService;
import com.coderscampus.chatapp.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@CrossOrigin(origins = "*")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Message>> getMessagesById(@PathVariable String userId, HttpServletRequest request) {
        try {
            System.out.println("We are in /messages/{userId}");
            UserInformation userInformation = userService.getUserInfo(request);
            System.out.println("userId: " + userId);
            System.out.println("userInformationId: " + userInformation);
            List<Message> messages = messageService.findBySenderOrRecipient(userId, userInformation.getUserId());
//            List<Message> messages = messageRepository.findAll();
            System.out.println(messages);
            return ResponseEntity.ok(messages);
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
