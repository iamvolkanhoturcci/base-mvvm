package com.domain.remote.exception;

public class ApiException extends Exception {
    private String title, message;
    private int code;

    public ApiException(String message, String title, int code) {
        super(message);
        this.message = message;
        this.code = code;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
