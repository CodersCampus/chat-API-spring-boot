package com.coderscampus.chatapp.controllers;

import com.coderscampus.chatapp.domain.User;
import com.coderscampus.chatapp.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new User(user.getUsername(), user.getPassword(), user.getOnline(), user.getCraetedAt(),user.getId() ))
                .collect(Collectors.toList());
    }


}

