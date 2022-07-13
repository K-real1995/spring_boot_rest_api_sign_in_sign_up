package com.kirillsoklakov.application.service;

import com.kirillsoklakov.application.models.AppException;
import com.kirillsoklakov.application.models.AppResponseCode;
import com.kirillsoklakov.application.models.DTO.user.SignInDto;
import com.kirillsoklakov.application.models.DTO.user.SignUpDto;
import com.kirillsoklakov.application.models.entity.OAuthAccessToken;
import com.kirillsoklakov.application.models.entity.User;
import com.kirillsoklakov.application.repository.TokenRepository;
import com.kirillsoklakov.application.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
//Имплементация сервиса регистрации и авторизации с реализацией методов
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
    }

    public OAuthAccessToken signUp(SignUpDto signUpDto) {
        User user = saveUser(signUpDto);
        return authorize(user);
    }

    public OAuthAccessToken signIn(SignInDto signInDto) {
        User user = new User(signInDto.getUsername(), signInDto.getPassword());
        validateUser(user);
        user = generateUser(signInDto.getUsername(), signInDto.getPassword());
        return authorize(user);
    }

    private User saveUser(SignUpDto signUpDto) {
        checkNewUser(signUpDto.getUsername());
        User user = generateUser(signUpDto.getUsername(), signUpDto.getPassword());
        user = userRepository.save(user);
        return user;
    }

    private User generateUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        return user;
    }

    private void checkNewUser(String username) {
        userRepository.findByUsername(username).ifPresent(user -> {
            throw new AppException(AppResponseCode.USER_ALREADY_EXISTS);
        });
    }

    private void validateUser(User user) {
        Optional<User> existsUser = userRepository.findByUsername(user.getUsername());
        if (!existsUser.isPresent()) {
            throw new AppException(AppResponseCode.USER_IS_NOT_FOUND);
        }
        if (!passwordEncoder.matches(user.getPassword(), existsUser.get().getPassword())) {
            throw new AppException(AppResponseCode.INVALID_PASSWORD);
        }
    }

    private OAuthAccessToken authorize(User user) {
        Optional<OAuthAccessToken> token = tokenRepository.findByUsername(user.getUsername());
        OAuthAccessToken accessToken;
        if (token.isPresent()) {
            accessToken = token.get();
        } else {
            accessToken = new OAuthAccessToken(user.getUsername());
            tokenRepository.save(accessToken);
        }
        return accessToken;
    }
}
