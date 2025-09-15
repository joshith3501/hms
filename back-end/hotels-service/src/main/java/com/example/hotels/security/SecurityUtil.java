package com.example.hotels.security;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

public class SecurityUtil {
    public static void requireRole(String rolesHeader, String requiredRole) {
        if (rolesHeader == null || rolesHeader.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Missing roles header");
        }
        String[] roles = rolesHeader.split(",");
        for (String r : roles) {
            if (r.trim().equalsIgnoreCase(requiredRole)) return;
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Permission denied: requires " + requiredRole);
    }
}
