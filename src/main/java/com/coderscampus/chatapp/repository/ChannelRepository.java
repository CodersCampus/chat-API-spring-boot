package com.coderscampus.chatapp.repository;

import com.coderscampus.chatapp.domain.Channel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChannelRepository extends MongoRepository<Channel, String> {

}
