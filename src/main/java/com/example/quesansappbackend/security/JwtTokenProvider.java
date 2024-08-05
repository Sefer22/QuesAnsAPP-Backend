package com.example.quesansappbackend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.secret}")
    private String APP_SECRET;//bu keye gore token yaradiriq
    @Value("${quesansappbackend.expires.in}")
    private long EXPIRES_IN;//ne qeder vaxta tokenler expire olur

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        // Açarı təmin etmək üçün `Keys.secretKeyFor` metodundan istifadə edin
        this.secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }


    public String generateJwtToken(Authentication authentication) {
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();//authenticate edeceyimiz user
        Date expireDate = new Date(new Date().getTime()+EXPIRES_IN);
        return Jwts.builder().setSubject(Long.toString(jwtUserDetails.getId()))
                .setIssuedAt(new Date()).setExpiration(expireDate)
                .signWith(secretKey, SignatureAlgorithm.HS256).compact();
    }
    Long getUserIdFromJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).build().parseSignedClaims(token).getPayload();
        return Long.parseLong(claims.getSubject());
    }

    boolean validateToken(String token) {
        try{
            Jwts.parser().setSigningKey(secretKey).build().parseSignedClaims(token);
            return !isTokenExpired(token);
        }catch (SignatureException e) {
            return false;
        }catch (MalformedJwtException e) {
            return false;
        }catch (ExpiredJwtException e) {
            return false;
        }catch (UnsupportedJwtException e) {
            return false;
        }catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = (Date) Jwts.parser().setSigningKey(secretKey).build().parseSignedClaims(token).getPayload().getExpiration();
        return expiration.before(new Date());
    }

    public String generateJwtTokenByUserId(Long userId) {
        Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
        return Jwts.builder()
                .setSubject(Long.toString(userId))
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }
}
