package com.kirillsoklakov.application.service;



import com.kirillsoklakov.application.models.entity.OAuthAccessToken;
import com.kirillsoklakov.application.models.entity.User;

import javax.servlet.http.HttpServletRequest;
//Интерфейс сервиса токенов
public interface TokenService {

    OAuthAccessToken findTokenForUser(String username);

    User findUserByToken(String token);

    Boolean isPresent(String token);

    OAuthAccessToken getTokenFromRequest(HttpServletRequest request);
}
