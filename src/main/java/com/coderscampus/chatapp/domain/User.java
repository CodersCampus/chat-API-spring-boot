package com.coderscampus.chatapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Document(collection = "users")
public class User implements UserDetails {

    @Id
//    @JsonIgnore
    private String id;
    private String username;

    private String password;
    private Boolean isOnline;
    private LocalDateTime craetedAt;

    public Boolean getOnline() {
        return isOnline;
    }

    public User() {
    }

    public User(String username, String password, Boolean isOnline, LocalDateTime craetedAt, String id) {
        this.username = username;
        this.password = password;
        this.isOnline = isOnline;
        this.craetedAt = craetedAt;
        this.id = id;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public LocalDateTime getCraetedAt() {
        return craetedAt;
    }

    public void setCraetedAt(LocalDateTime craetedAt) {
        this.craetedAt = craetedAt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public void setPassword(String password) {
        this.password = password;
    }
}
