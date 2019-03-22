package com.jpdou.m2review.model.http;

public class Response {
    private boolean status;
    private String message;

    public Response() {
        this.status = false;
        this.message = "";
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
