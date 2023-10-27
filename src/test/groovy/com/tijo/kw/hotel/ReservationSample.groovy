package com.tijo.kw.hotel

import com.tijo.kw.hotel.dto.ReservationDto
import java.time.LocalDate

trait ReservationSample implements RoomSample, UserSample {

    def RESERVATION_ID = UUID.randomUUID()
    def START_DATE = LocalDate.parse("2030-06-06")
    def END_DATE =LocalDate.parse("2030-06-08")

    private Map<String, Object> DEFAULT_RESERVATION = [
            id          : RESERVATION_ID,
            startDate   : START_DATE,
            endDate     : END_DATE,
            userId      : USER_ID,
            roomId      : ROOM_ID,
            allInclusive: true,
            price       : 250.00
    ] as Map<String, Object>

    ReservationDto createReservation(Map<String, Object> changes = [:]) {
        def result = DEFAULT_RESERVATION + changes
        ReservationDto.builder()
                .id(result.id as UUID)
                .startDate(result.startDate as LocalDate)
                .endDate(result.endDate as LocalDate)
                .userId(result.userId as UUID)
                .roomId(result.roomId as UUID)
                .allInclusive(result.allInclusive as boolean)
                .price(result.price as double)
                .build()
    }

}
