package com.coderscampus.chatapp.controllers;


import com.coderscampus.chatapp.domain.User;
import com.coderscampus.chatapp.dto.AuthResponse;
import com.coderscampus.chatapp.repository.UserRepository;
import com.coderscampus.chatapp.security.CustomUserDetailsService;
import com.coderscampus.chatapp.security.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User user){


        User foundUser =  userRepository.findByUsername(user.getUsername());
        if(foundUser != null) {
            Map<String, Boolean> response = new HashMap<>();
            response.put("isUserExist", true);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        else {
            User newUser = new User();
           newUser.setUsername(user.getUsername());
           newUser.setPassword(passwordEncoder.encode(user.getPassword()));

            User savedUser = userRepository.save(newUser);
            String token = jwtUtil.generateToken(savedUser.getUsername(), savedUser.getId());

            return ResponseEntity.status(HttpStatus.CREATED)
                    .header("Set-Cookie","token="+token+"; Secure; SameSite:None")
                    .body(new AuthResponse(token, savedUser.getId()));

        }



    }
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody User user, HttpServletResponse response){

        User foundUser =  userRepository.findByUsername(user.getUsername());

        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);

        // Generate JWT
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails, foundUser.getId());
        System.out.println(jwt);

        if (foundUser.getId() != null) {
            Cookie cookie = new Cookie("token" ,jwt);
            cookie.setHttpOnly(true); // Set HTTP only to prevent XSS attacks
            cookie.setMaxAge(3600); // Set expiry time in seconds
            cookie.setSecure(true); // Set to true if your application is running over HTTPS
            cookie.setPath("/"); // Set cookie path to root
            response.addCookie(cookie);
            return ResponseEntity.ok(new AuthResponse(jwt, foundUser.getId()));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        SecurityContextHolder.clearContext();
            return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);
    }
}
