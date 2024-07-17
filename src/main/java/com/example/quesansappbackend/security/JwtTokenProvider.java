package com.example.quesansappbackend.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    @Value("${}")
    private String APP_SECRET;//bu keye gore token yaradiriq
    @Value("${quesansappbackend.expires.in}")
    private long EXPIRES_IN;//ne qeder vaxta tokenler expire olur
}
