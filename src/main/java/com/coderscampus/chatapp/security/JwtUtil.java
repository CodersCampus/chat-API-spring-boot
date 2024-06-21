package com.coderscampus.chatapp.security;

import com.coderscampus.chatapp.dto.UserInformation;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class    JwtUtil {

    private final String secret = "x11231dadsakd12k3j12sadas1231adadasd123213dasdasda23a3";


    public String generateToken(UserDetails userDetails, String userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("username", userDetails.getUsername());
        System.out.println(createToken(claims, userDetails.getUsername()));
        return createToken(claims, userDetails.getUsername());
    }
    public String generateToken(String username, String userId) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("username", username);
        return createToken(claims, username);
    }

    public UserInformation parseToken(String token) {

        System.out.println(token);
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();

        String userId = (String)claims.get("userId");
        String username = (String) claims.get("username"); // Assuming username is stored in the JWT claims

        return new UserInformation(userId, username);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    private String createToken(Map<String, Object> claims, String subject) {
        // Get the current time
        Date issuedAt = new Date(System.currentTimeMillis());

        // Calculate the expiration time (10 hours from now)
        LocalDateTime expirationLocalDateTime = LocalDateTime.now().plusHours(10);
        ZoneId zoneId = ZoneId.of("UTC"); // Use UTC or your desired time zone
        Date expirationDate = Date.from(expirationLocalDateTime.atZone(zoneId).toInstant());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(issuedAt)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

}