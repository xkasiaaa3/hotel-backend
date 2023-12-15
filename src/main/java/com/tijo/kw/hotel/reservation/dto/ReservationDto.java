package com.tijo.kw.hotel.reservation.dto;

import com.tijo.kw.hotel.reservation.entity.Reservation;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@EqualsAndHashCode
public class ReservationDto {

    UUID id;
    LocalDate startDate;
    LocalDate endDate;
    UUID userId;
    UUID roomId;
    boolean allInclusive;
    double price;

    public Reservation toEntity() {
        return Reservation.builder()
                .allInclusive(allInclusive)
                .endDate(endDate)
                .price(price)
                .startDate(startDate)
                .id(id)
                .roomId(roomId)
                .userId(userId)
                .build();
    }

    public static ReservationDto fromEntity(Reservation reservation) {
        return ReservationDto.builder()
                .allInclusive(reservation.isAllInclusive())
                .endDate(reservation.getEndDate())
                .price(reservation.getPrice())
                .startDate(reservation.getStartDate())
                .id(reservation.getId())
                .roomId(reservation.getRoomId())
                .userId(reservation.getUserId())
                .build();
    }

}
