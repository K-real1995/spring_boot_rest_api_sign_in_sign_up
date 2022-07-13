package com.kirillsoklakov.application.filter;

import com.kirillsoklakov.application.models.AppException;
import com.kirillsoklakov.application.models.AppResponseCode;
import com.kirillsoklakov.application.service.RequestService;
import com.kirillsoklakov.application.service.TokenService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class AuthorizationFilter implements Filter {

    private final RequestService requestService;

    private final TokenService tokenService;

    private final String[] PUBLIC_URLS = {
            "/user/sign-up", "/user/sign-in", "/error"
    };

    private final String[] PROTECTED_URLS = {
            "/user/sign-up"
    };

    public AuthorizationFilter(RequestService requestService, TokenService tokenService) {
        this.requestService = requestService;
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        validateUriForUser(httpServletRequest, httpServletResponse);
        requestService.saveRequest(httpServletRequest);
        if (Arrays.stream(PUBLIC_URLS).noneMatch(s -> httpServletRequest.getRequestURI().equals(s))) {
            validateUserAuth(httpServletRequest, httpServletResponse);
        }
        chain.doFilter(request, response);
    }
    //Метод выбрасывающий ошибки на неверные запросы
    private void validateUserAuth(HttpServletRequest request, HttpServletResponse response) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || authHeader.isEmpty()) {
            throw new AppException(AppResponseCode.AUTHORIZATION_HEADER_EMPTY);
        }
        if (authHeader.split(" ").length != 2) {
            throw new AppException(AppResponseCode.AUTHORIZATION_HEADER_WRONG_FORMAT);
        }
        tokenService.getTokenFromRequest(request);
    }
    //Метод ограничивающий количество запросов до 10
    private void validateUriForUser(HttpServletRequest request, HttpServletResponse response) {
        String currentUri = request.getRequestURI();
        if (Arrays.asList(PROTECTED_URLS).contains(currentUri)) {
            String currentIp = request.getRemoteHost();
            int countRequestsByIp = requestService.findLastForHourRequestsForIp(currentIp).size();
            if (countRequestsByIp > 10) {
                throw new AppException(AppResponseCode.TOO_MANY_REQUESTS);
            }
        }

    }
}
