package com.tijo.kw.hotel.security.auth.exception;

public class NoSuchUser extends RuntimeException {
    public NoSuchUser(String errorMessage) {
        super(errorMessage);
    }
}
