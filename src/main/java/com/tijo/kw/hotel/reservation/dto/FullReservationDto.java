package com.tijo.kw.hotel.reservation.dto;

import com.tijo.kw.hotel.room.dto.RoomDto;
import com.tijo.kw.hotel.room.dto.TypeOfRoomDto;
import com.tijo.kw.hotel.user.dto.UserDetailsDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
public class FullReservationDto {

    UUID reservationId;
    LocalDate startDate;
    LocalDate endDate;
    boolean allInclusive;
    double price;
    String firstName;
    String lastName;
    String email;
    Integer roomNumber;
    Integer roomFloor;
    String typeOfRoomName;
    Integer numberOfPeople;

    public static FullReservationDto makeFullReservation(ReservationDto reservation, UserDetailsDto user, TypeOfRoomDto typeOfRoom, RoomDto room) {
        return FullReservationDto.builder()
                .reservationId(reservation.getId())
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .allInclusive(reservation.allInclusive)
                .price(reservation.getPrice())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .roomNumber(room.getNumber())
                .roomFloor(room.getFloor())
                .typeOfRoomName(typeOfRoom.getName())
                .numberOfPeople(typeOfRoom.getNumberOfPeople())
                .build();
    }

}
