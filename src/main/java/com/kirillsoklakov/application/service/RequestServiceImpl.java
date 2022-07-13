package com.kirillsoklakov.application.service;

import com.kirillsoklakov.application.models.entity.Request;
import com.kirillsoklakov.application.repository.RequestRepository;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
//Имплементация сервиса запросов с реализацией методов
@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final TokenService tokenService;

    public RequestServiceImpl(RequestRepository requestRepository, TokenService tokenService) {
        this.requestRepository = requestRepository;
        this.tokenService = tokenService;
    }

    @Override
    public Request saveRequest(HttpServletRequest request) {
        return requestRepository.save(buildRequest(request));
    }

    @Override
    public List<Request> findLastForHourRequestsForIp(String ip) {
        return requestRepository.findAllByIp(ip)
                .stream()
                .filter(r -> r.getTime().isAfter(LocalDateTime.now().minusHours(1)))
                .collect(Collectors.toList());
    }

    private Request buildRequest(HttpServletRequest request) {
        Request result = new Request();
        result.setIp(request.getRemoteHost());
        result.setTime(LocalDateTime.now());
        result.setUri(request.getRequestURI());
        if (tokenService.isPresent(request.getHeader("Authorization")))
            result.setUsername(tokenService.findUserByToken(request.getHeader("Authorization")).getUsername());
        else
            result.setUsername("anonymous");
        return result;
    }
}
