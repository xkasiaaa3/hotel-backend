package com.tijo.kw.hotel.room.dto;

import com.tijo.kw.hotel.room.entity.Room;
import com.tijo.kw.hotel.room.exception.InvalidValuesException;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@EqualsAndHashCode
public class RoomDto {

    UUID id;
    Integer number;
    UUID typeId;
    Integer floor;

    public static RoomDto fromEntity(Room room) {
        return RoomDto.builder()
                .id(room.getId())
                .typeId(room.getTypeId())
                .number(room.getNumber())
                .floor(room.getFloor())
                .build();
    }

    public Room toEntity() {
        return Room.builder()
                .id(id)
                .number(number)
                .typeId(typeId)
                .floor(floor)
                .build();
    }

    public void validate() {
        if (floor == null || floor < 0) {
            throw new InvalidValuesException("Floor");
        }

        if (number == null || number < 0) {
            throw new InvalidValuesException("Room number");
        }


    }
}
