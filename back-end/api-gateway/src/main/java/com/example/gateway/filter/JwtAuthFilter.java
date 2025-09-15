package com.example.gateway.filter;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.Key;
import java.util.List;

@Component
public class JwtAuthFilter implements GlobalFilter, Ordered {

    @Value("${jwt.secret:ReplaceWithLongRandomSecretKeyAtLeast32Chars}")
    private String jwtSecret;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().toString();
        if (path.startsWith("/auth") || path.contains("/eureka") || path.startsWith("/actuator")) {
            return chain.filter(exchange);
        }

        List<String> auth = exchange.getRequest().getHeaders().getOrEmpty("Authorization");
        if (auth.isEmpty() || !auth.get(0).startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        String token = auth.get(0).substring(7);

        try {
            Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            String username = claims.getBody().getSubject();
            Object roles = claims.getBody().get("roles");
            Object userIdObj = claims.getBody().get("userId");

            ServerWebExchange mutated = exchange.mutate()
                    .request(r -> r.header("X-User-Name", username == null ? "" : username)
                        .header("X-User-Roles", roles == null ? "" : roles.toString())
                        .header("X-User-Id", userIdObj == null ? "" : userIdObj.toString()))
                    .build();

            return chain.filter(mutated);
        } catch (JwtException e) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    @Override
    public int getOrder() { return -1; }
}
