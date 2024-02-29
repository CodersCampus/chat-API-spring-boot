package com.coderscampus.chatapp.services;

import com.coderscampus.chatapp.dto.UserInformation;
import com.coderscampus.chatapp.security.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Autowired
    private JwtUtil jwtUtil;
    private String getTokenFromRequest(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
    public UserInformation getUserInfo(HttpServletRequest request) {
        String token = getTokenFromRequest(request);


        if (token != null) {
            try {
                return jwtUtil.parseToken(token); // Assuming JwtService provides method to parse and extract user information from token
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error parsing token", e);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No token found");
        }
    }
}
