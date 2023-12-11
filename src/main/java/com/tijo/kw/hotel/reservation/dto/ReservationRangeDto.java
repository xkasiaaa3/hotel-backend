package com.tijo.kw.hotel.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@AllArgsConstructor
@Getter
public class ReservationRangeDto {

    LocalDate startDate;
    LocalDate endDate;

    public Long getDurationInDays(){
        return startDate.until(endDate, ChronoUnit.DAYS);
    }
}
