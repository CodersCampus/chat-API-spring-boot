package com.coderscampus.chatapp.services;

import com.coderscampus.chatapp.domain.Channel;
import com.coderscampus.chatapp.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService {
    @Autowired
    private ChannelRepository channelRepository;

    public Channel createChannel(Channel channel) {
    return channelRepository.save(channel);
    }
    public List<Channel> getAllChannels() {
        return channelRepository.findAll();
    }
}
