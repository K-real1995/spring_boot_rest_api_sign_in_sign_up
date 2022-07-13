package com.kirillsoklakov.application.models;
//Модель для исключений
public class AppException extends RuntimeException {

    private AppResponseCode code;

    private String errorMessage;

    public AppException(AppResponseCode code) {
        super(code.getMessage());
        this.code = code;
        this.errorMessage = code.getMessage();
    }

    public AppResponseCode getCode() {
        return code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
