package com.tijo.kw.hotel.room.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
public class RoomDto {

    UUID id;
    int number;
    UUID typeId;
    int floor;
    String photoUrl;


}
