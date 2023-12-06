package com.tijo.kw.hotel.room.exception;

public class DuplicateNumberException extends RuntimeException {

    public DuplicateNumberException(String errorMessage) {
        super(errorMessage);
    }
}
