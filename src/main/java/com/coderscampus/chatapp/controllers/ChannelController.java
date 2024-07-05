package com.coderscampus.chatapp.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChannelController {

    @PostMapping("/create-channel")
    public String createChannel () {
        return "Channel created";
    }
}
