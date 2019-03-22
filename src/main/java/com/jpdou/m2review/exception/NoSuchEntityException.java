package com.jpdou.m2review.exception;

public class NoSuchEntityException extends LocalizedException {
    public NoSuchEntityException() {
        super();
    }

    public NoSuchEntityException(String message) {
        super(message);
    }
}
