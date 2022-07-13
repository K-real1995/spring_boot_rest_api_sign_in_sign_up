package com.kirillsoklakov.application.models.entity;

import javax.persistence.*;
import java.util.UUID;
// Entity для токена авторизации
@Entity
public class OAuthAccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String accessToken;
    private String tokenType;
    private String username;

    public OAuthAccessToken() {
    }

    public OAuthAccessToken(String username) {
        this.accessToken = UUID.randomUUID().toString();
        this.tokenType = "Bearer".toLowerCase();
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
