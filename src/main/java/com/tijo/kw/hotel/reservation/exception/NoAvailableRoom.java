package com.tijo.kw.hotel.reservation.exception;

public class NoAvailableRoom extends RuntimeException {

    public NoAvailableRoom(String errorMessage) {
        super(errorMessage);
    }
}
