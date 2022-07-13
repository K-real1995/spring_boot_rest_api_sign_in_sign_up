package com.kirillsoklakov.application.payload;
// Полезная нагрузка для вывода сообщений для ряда контроллеров
public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
