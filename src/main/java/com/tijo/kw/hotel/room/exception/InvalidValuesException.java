package com.tijo.kw.hotel.room.exception;

public class InvalidValuesException extends RuntimeException {

    public InvalidValuesException(String value) {
        super(value + " is invalidt");
    }
}
