package com.tijo.kw.hotel.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
public class ReservationDto {

    UUID id;
    LocalDate startDate;
    LocalDate endDate;
    UUID userId;
    UUID roomId;
    boolean allInclusive;
    double price;

}
