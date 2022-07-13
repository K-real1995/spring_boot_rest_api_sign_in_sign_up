package com.kirillsoklakov.application.service;

import com.kirillsoklakov.application.models.DTO.user.SignInDto;
import com.kirillsoklakov.application.models.DTO.user.SignUpDto;
import com.kirillsoklakov.application.models.entity.OAuthAccessToken;

//Интерфейс сервиса регистрации и авторизации
public interface UserService {

    OAuthAccessToken signUp(SignUpDto signUpDto);

    OAuthAccessToken signIn(SignInDto signInDto);

}
