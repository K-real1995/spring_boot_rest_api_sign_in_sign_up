package com.kirillsoklakov.application.config;

import com.kirillsoklakov.application.filter.AuthorizationFilter;
import com.kirillsoklakov.application.service.RequestService;
import com.kirillsoklakov.application.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CorsFilter;

//Создаем класс конфигурации безопасности
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private RequestService requestService;
    @Autowired
    private TokenService tokenService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(new AuthorizationFilter(requestService, tokenService), CorsFilter.class)
                .csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}