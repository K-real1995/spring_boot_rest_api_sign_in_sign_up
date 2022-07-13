package com.kirillsoklakov.application.models;
// Enum в перечнем ошибок и методами сообщений
public enum AppResponseCode {
    UNKNOWN(0, "Unknown exception"),
    USER_IS_NOT_FOUND(1, "User isn't found, please try again"),
    USER_ALREADY_EXISTS(2, "User already exists"),
    INVALID_PASSWORD(3, "Invalid password, please try again"),
    AUTHORIZATION_HEADER_EMPTY(4, "Authorization header empty"),
    AUTHORIZATION_HEADER_WRONG_FORMAT(5, "Authorization header wrong format"),
    INCORRECT_AUTHORIZATION_TOKEN(6, "Incorrect authorization token"),
    PET_ALREADY_EXISTS(7, "Pet already exists"),
    PET_IS_NOT_FOUND(8, "Pet isn't found"),
    TOKEN_NOT_FOUND(9, "Token not found"),
    USER_WITH_THIS_TOKEN_NOT_FOUND(10, "User with this token not found"),
    PET_HAS_ANOTHER_OWNER_ACCSESS_DENIED(11, "Pet has another owner. Access denied"),
    TOO_MANY_REQUESTS(12, "Count of your requests is more than 10, please try again after one hour")
    ;

    private final int code;

    private final String message;

    AppResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
