package com.kirillsoklakov.application.service;

import com.kirillsoklakov.application.models.entity.Request;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
//Интерфейс сервиса запросов
public interface RequestService {

    Request saveRequest(HttpServletRequest request);

    List<Request> findLastForHourRequestsForIp(String ip);

}
