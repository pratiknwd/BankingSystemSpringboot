package com.virtusa.springboot.BankingSystemSpringBoot.security.jwt;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.virtusa.springboot.BankingSystemSpringBoot.exception.AuthTokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtilService {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtilService.class);

    @Value("${banking.system.jwtSecret}")
    private String jwtSecret;

    @Value("${banking.system.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Set<GrantedAuthority> extractAuthorities(String token){
        Set<GrantedAuthority> authorities = new HashSet<>();

        @SuppressWarnings("unchecked")
        List<String> authList = extractClaim(token,claims->claims.get("authorities",List.class));
        
        authList.forEach(authority->{
            authorities.add(new SimpleGrantedAuthority(authority));
        });
        
        return authorities;
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(getSignKey()).build()
                .parseClaimsJws(token).getBody();

        return claims;
    }

    public Boolean validateToken(String token) {

        try {
            Jwts.parserBuilder().setSigningKey(getSignKey()).build().parse(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
            throw new AuthTokenException(String.format("Invalid JWT token: %s", e.getMessage()));
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
            throw new AuthTokenException(String.format("JWT token is expired: %s", e.getMessage()));
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
            throw new AuthTokenException(String.format("JWT token is unsupported: %s", e.getMessage()));
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
            throw new AuthTokenException(String.format("JWT claims string is empty: %s", e.getMessage()));
        }

    }

    public String GenerateToken(String username, Set<GrantedAuthority> authorities) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities",authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
