package com.coderscampus.chatapp.dto;

public class AuthResponse {
    private final String jwt;
    private final String _id;
    public AuthResponse(String jwt, String id) {
        this.jwt = jwt;
        this._id = id;
    }

    public String getJwt() {
        return jwt;
    }

    public String get_id() {
        return _id;
    }
}
