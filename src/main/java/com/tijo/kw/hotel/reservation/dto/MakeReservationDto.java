package com.tijo.kw.hotel.reservation.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class MakeReservationDto {
    UUID typeOfRoomId;
    ReservationRangeDto reservationRange;
    boolean allInclusive;
    UUID userId;
}
