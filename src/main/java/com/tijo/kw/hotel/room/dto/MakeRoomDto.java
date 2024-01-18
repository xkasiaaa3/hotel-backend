package com.tijo.kw.hotel.room.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class MakeRoomDto {

    Integer number;
    UUID typeId;
    Integer floor;

    public RoomDto toRoomDto(){

        return RoomDto.builder()
                .id(UUID.randomUUID())
                .number(number)
                .typeId(typeId)
                .floor(floor)
                .build();
    }
}
