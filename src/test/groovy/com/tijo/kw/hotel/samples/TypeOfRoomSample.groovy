package com.tijo.kw.hotel.samples

import com.tijo.kw.hotel.room.dto.TypeOfRoomDto

trait TypeOfRoomSample {

    def ROOM_TYPE_ID = UUID.randomUUID()

    private Map<String, Object> DEFAULT_ROOM_TYPE = [
            id            : ROOM_TYPE_ID,
            name          : "Pokój dwuosobowy",
            numberOfPeople: 2,
            numberOfBeds  : 1,
            bathroom      : true,
            balcony       : false,
            pricePerDay   : 100.00
    ] as Map<String, Object>


    TypeOfRoomDto createTypeOfRoom(Map<String, Object> changes = [:]) {
        def result = DEFAULT_ROOM_TYPE + changes
        TypeOfRoomDto.builder()
                .id(result.id as UUID)
                .name(result.name as String)
                .numberOfPeople(result.numberOfPeople as Integer)
                .numberOfBeds(result.numberOfBeds as Integer)
                .bathroom(result.bathroom as boolean)
                .balcony(result.balcony as boolean)
                .pricePerDay(result.pricePerDay as double)
                .build()
    }
}
