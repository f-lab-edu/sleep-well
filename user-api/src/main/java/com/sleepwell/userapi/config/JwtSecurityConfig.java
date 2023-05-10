package com.sleepwell.userapi.config;

import com.sleepwell.userapi.util.JwtFilter;
import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtFilter jwtFilter;

    // JwtFilter 를 UsernamePasswordAuthenticationFilter 앞에 등록
    @Override
    public void configure(HttpSecurity http) {
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
