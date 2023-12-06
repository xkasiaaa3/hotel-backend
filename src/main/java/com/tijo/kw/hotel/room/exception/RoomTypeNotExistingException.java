package com.tijo.kw.hotel.room.exception;

public class RoomTypeNotExistingException extends RuntimeException {

    public RoomTypeNotExistingException(String errorMessage) {
        super(errorMessage);
    }
}
