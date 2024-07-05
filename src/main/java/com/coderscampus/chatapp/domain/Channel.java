package com.coderscampus.chatapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "channels")
public class Channel {
    @Id
    @JsonIgnore
    private String id;

    private List<User> users = new ArrayList<>();

    public Channel(String id, List<User> users) {
        this.id = id;
        this.users = users;
    }

    public Channel() {
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
