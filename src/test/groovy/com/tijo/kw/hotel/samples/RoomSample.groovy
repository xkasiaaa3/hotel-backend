package com.tijo.kw.hotel.samples

import com.tijo.kw.hotel.room.dto.MakeRoomDto
import com.tijo.kw.hotel.room.dto.RoomDto

trait RoomSample implements TypeOfRoomSample {

    static UUID ROOM_ID = UUID.randomUUID()

    private Map<String, Object> DEFAULT_ROOM = [
            id    : ROOM_ID,
            number: 1,
            typeId: ROOM_TYPE_ID,
            floor : 1
    ] as Map<String, Object>

    RoomDto createRoom(Map<String, Object> changes = [:]) {
        def result = DEFAULT_ROOM + changes
        RoomDto.builder()
                .id(result.id as UUID)
                .number(result.number as Integer)
                .typeId(result.typeId as UUID)
                .floor(result.floor as Integer)
                .build()
    }

    MakeRoomDto createRoomRequest(Map<String, Object> changes = [:]) {
        def result = DEFAULT_ROOM + changes
        MakeRoomDto.builder()
                .number(result.number as Integer)
                .typeId(result.typeId as UUID)
                .floor(result.floor as Integer)
                .build()
    }

}
