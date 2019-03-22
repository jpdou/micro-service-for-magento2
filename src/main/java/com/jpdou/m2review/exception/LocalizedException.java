package com.jpdou.m2review.exception;

abstract class LocalizedException extends Exception {

    public LocalizedException() {
    }

    public LocalizedException(String message) {
        super(message);
    }
}
