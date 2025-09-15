package com.example.user.controller;

import com.example.user.entity.UserAccount;
import com.example.user.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository repo;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret:eo2q5HbX4/yo1VXlIIO0SDoYM/fb5bOHhb8EZaCDjIE=}")
    private String jwtSecret;

    @Value("${jwt.expiration-ms:86400000}")
    private long jwtExpirationMs;

    public AuthController(UserRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");

        UserAccount u = repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(password, u.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        String jws = Jwts.builder()
                .setSubject(u.getUsername())
                .claim("userId", u.getId())
                .claim("roles", u.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return Map.of("token", jws);
    }
}
