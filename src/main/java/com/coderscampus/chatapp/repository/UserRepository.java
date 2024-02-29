package com.coderscampus.chatapp.repository;

import com.coderscampus.chatapp.domain.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;

@Configuration
public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
}
