package com.tijo.kw.hotel.samples

import com.tijo.kw.hotel.reservation.dto.MakeReservationDto
import com.tijo.kw.hotel.reservation.dto.ReservationDto
import com.tijo.kw.hotel.reservation.dto.ReservationRangeDto

import java.time.LocalDate

trait ReservationSample implements RoomSample, UserSample {

    static UUID RESERVATION_ID = UUID.randomUUID()
    static LocalDate START_DATE = LocalDate.parse("2030-06-05")
    static LocalDate END_DATE = LocalDate.parse("2030-06-08")

    private Map<String, Object> DEFAULT_RESERVATION = [
            id          : RESERVATION_ID,
            startDate   : START_DATE,
            endDate     : END_DATE,
            userId      : USER_ID,
            roomId      : ROOM_ID,
            allInclusive: true,
            price       : 300.00
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

    private Map<String, Object> DEFAULT_RESERVATION_RANGE = [
            startDate: START_DATE,
            endDate  : END_DATE,
    ] as Map<String, Object>

    ReservationRangeDto createReservationRange(Map<String, Object> changes = [:]) {
        def result = DEFAULT_RESERVATION_RANGE + changes
        new ReservationRangeDto(result.startDate as LocalDate, result.endDate as LocalDate)
    }

    private Map<String, Object> DEFAULT_RESERVATION_REQUEST = [
            reservationRange: createReservationRange(),
            userId          : USER_ID,
            typeOfRoomId    : ROOM_TYPE_ID,
            allInclusive    : true,
    ] as Map<String, Object>

    MakeReservationDto createReservationRequest(Map<String, Object> changes = [:]) {
        def result = DEFAULT_RESERVATION_REQUEST + changes
        MakeReservationDto.builder()
                .reservationRange(result.reservationRange as ReservationRangeDto)
                .userId(result.userId as UUID)
                .typeOfRoomId(result.typeOfRoomId as UUID)
                .allInclusive(result.allInclusive as boolean)
                .build()
    }

}
