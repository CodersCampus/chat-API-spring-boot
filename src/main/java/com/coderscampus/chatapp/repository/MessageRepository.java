package com.coderscampus.chatapp.repository;

import com.coderscampus.chatapp.domain.Message;
import com.coderscampus.chatapp.domain.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {




//    List<Message> findBySenderAndRecipient(String senderId, String recipientId);
@Query("{$or:[{$and:[{sender: ?0}, {recipient: ?1}]}, {$and:[{sender: ?1}, {recipient: ?0}]}]}")
List<Message> findMessagesBetweenUsers(String userId1, String userId2);
}

