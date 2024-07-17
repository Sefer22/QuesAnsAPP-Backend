package com.example.quesansappbackend.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${}")
    private String APP_SECRET;//bu keye gore token yaradiriq
    @Value("${quesansappbackend.expires.in}")
    private long EXPIRES_IN;//ne qeder vaxta tokenler expire olur

    public String generateJwtToken(Authentication authentication) {
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();//authenticate edeceyimiz user
        Date expireDate = new Date(new Date().getTime()+EXPIRES_IN);
        return Jwts.builder().setSubject(Long.toString(jwtUserDetails.getId()))
                .setIssuedAt(new Date()).setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,APP_SECRET).compact();
    }
}
