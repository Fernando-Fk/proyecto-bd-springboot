package com.edutrack.academico.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    private Key key;

    @PostConstruct
    public void initKey() {
        if (jwtSecret == null || jwtSecret.isBlank()) {
            // Intenta obtener de la variable de entorno si @Value falla
            jwtSecret = System.getenv("JWT_SECRET");
        }

        if (jwtSecret == null || jwtSecret.isBlank()) {
            throw new IllegalStateException("No se encontró JWT_SECRET.");
        }

        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret.trim());
        if (keyBytes.length < 32) {
            throw new IllegalStateException("JWT_SECRET debe ser al menos 256 bits (32 bytes).");
        }

        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    private Key getKey() {
        if (key == null) {
            initKey();
        }
        return key;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // 24h
                .signWith(getKey())
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }
}
