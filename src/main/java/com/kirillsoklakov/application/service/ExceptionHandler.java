package com.kirillsoklakov.application.service;

import com.kirillsoklakov.application.models.AppError;
import com.kirillsoklakov.application.models.AppException;
import com.kirillsoklakov.application.models.AppResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//Сервис для выброса исключения
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<AppError> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new AppError(AppResponseCode.UNKNOWN.getCode(), ex.getMessage()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AppException.class)
    @ResponseBody
    public ResponseEntity<AppError> handleAppException(AppException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AppError(ex.getCode().getCode(), ex.getMessage()));
    }
}
