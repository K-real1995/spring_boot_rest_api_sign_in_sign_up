package com.kirillsoklakov.application.controller;

import com.kirillsoklakov.application.models.DTO.user.SignInDto;
import com.kirillsoklakov.application.models.DTO.user.SignUpDto;
import com.kirillsoklakov.application.models.entity.OAuthAccessToken;
import com.kirillsoklakov.application.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

//Создаем контроллер пользователей
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    //Post запрос на регистрацию пользователя
    @PostMapping("/sign-up")
    public ResponseEntity<OAuthAccessToken> signUp(@RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok(userService.signUp(signUpDto));
    }
    //Post запрос на авторизацию пользователя
    @PostMapping("/sign-in")
    public ResponseEntity<OAuthAccessToken> signIn(@RequestBody SignInDto signInDto) {
        return ResponseEntity.ok(userService.signIn(signInDto));
    }

}
