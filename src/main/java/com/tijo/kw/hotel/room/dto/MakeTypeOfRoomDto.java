package com.tijo.kw.hotel.room.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;


@Builder
@Getter
public class MakeTypeOfRoomDto {

    String name;
    Integer numberOfPeople;
    Integer numberOfBeds;
    Boolean bathroom;
    Boolean balcony;
    Double pricePerDay;
    String photoUrl;

    public TypeOfRoomDto toTypeOfRoom() {

        return TypeOfRoomDto.builder()
                .id(UUID.randomUUID())
                .numberOfBeds(numberOfBeds)
                .numberOfPeople(numberOfPeople)
                .name(name)
                .balcony(balcony)
                .bathroom(bathroom)
                .pricePerDay(pricePerDay)
                .photoUrl(photoUrl)
                .build();

    }

}
