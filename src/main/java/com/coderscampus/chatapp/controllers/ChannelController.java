package com.coderscampus.chatapp.controllers;

import com.coderscampus.chatapp.domain.Channel;
import com.coderscampus.chatapp.services.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChannelController {

    @Autowired
    private ChannelService channelService;
    @PostMapping("/create-channel")
    public String createChannel () {
        channelService.createChannel(new Channel());

        return "Channel created";
    }

    @GetMapping("/channels")
    public List<Channel> getChannels() {
        List<Channel> channels = channelService.getAllChannels();
        return channels;
    }
}
