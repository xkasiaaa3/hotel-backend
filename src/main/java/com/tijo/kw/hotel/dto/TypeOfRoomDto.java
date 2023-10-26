package com.tijo.kw.hotel.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class TypeOfRoomDto {

    UUID id;
    String name;
    int numberOfPeople;
    int numberOfBeds;
    boolean bathroom;
    boolean balcony;
    double pricePerDay;

}
