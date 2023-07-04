package com.sleepwell.userapi.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;

@Slf4j
@Setter
@Component
@ConfigurationProperties("jwt.provider")
public class JwtProvider {

    private final Key key;
    private long tokenValidateTime;

    public JwtProvider() {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }

    //토큰 생성
    public String createToken(Authentication authentication) {
        // 만료시간 계산
        long now = new Date().getTime();
        Date tokenExpiredTime = new Date(now + this.tokenValidateTime);

        // JWT 토큰 생성
        return Jwts.builder()
                .setSubject(authentication.getName()) // email
                .signWith(key, SignatureAlgorithm.HS512) // 서명 키 값
                .setExpiration(tokenExpiredTime) // 만료 시간
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);

        UserDetails principal = new User(claims.getSubject(), "", new ArrayList<>());
        return new UsernamePasswordAuthenticationToken(principal, token, new ArrayList<>());
    }

    //payload 내에 정보 가져오기
    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //유효한 JWT 토큰인지 검사
    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (SecurityException | MalformedJwtException exception) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }

        return false;
    }
}


