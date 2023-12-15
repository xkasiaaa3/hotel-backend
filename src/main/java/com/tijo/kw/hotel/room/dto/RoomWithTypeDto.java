package com.tijo.kw.hotel.room.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class RoomWithTypeDto {

    UUID roomId;
    Integer roomNumber;
    UUID typeId;
    Integer floor;
    String typeName;
    Double pricePerDay;

    public static RoomWithTypeDto makeRoomWithType(RoomDto room, TypeOfRoomDto type){
        return RoomWithTypeDto.builder()
                .roomId(room.getId())
                .roomNumber(room.getNumber())
                .typeId(room.getTypeId())
                .floor(room.getFloor())
                .typeName(type.getName())
                .pricePerDay(type.getPricePerDay())
                .build();
    }


}
