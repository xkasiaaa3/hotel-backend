package com.tijo.kw.hotel

import com.tijo.kw.hotel.room.domain.RoomFacade
import com.tijo.kw.hotel.room.dto.TypeOfRoomDto
import com.tijo.kw.hotel.room.exception.InvalidValuesException
import com.tijo.kw.hotel.room.repository.RoomRepository
import com.tijo.kw.hotel.room.repository.TypeOfRoomRepository
import com.tijo.kw.hotel.samples.TypeOfRoomSample
import spock.lang.Specification
import spock.lang.Unroll

class TypeOfRoomSpec extends Specification implements TypeOfRoomSample {
    RoomRepository roomRepository = new InMemoryRoomRepository();
    TypeOfRoomRepository typeOfRoomRepository = new InMemoryTypeOfRoomRepository();
    RoomFacade roomFacade = new RoomFacade(roomRepository, typeOfRoomRepository);

    def "Admin can add type of room with right values"() {
        when: "User adds the room with right values"
        TypeOfRoomDto newTypeOfRoom = roomFacade.addTypeOfRoom(createTypeOfRoom())
        then: "Room is added"
        newTypeOfRoom == createTypeOfRoom()
    }

    @Unroll
    def "Admin can't add a room with number of people or beds below 1"() {
        when: "User adds the room with invalid values"
        roomFacade.addTypeOfRoom(typeOfRoom)
        then: "Room is not added"
        thrown(InvalidValuesException)
        where:
        typeOfRoom << [createTypeOfRoom([numberOfBeds: 0]), createTypeOfRoom([numberOfPeople: 0]), createTypeOfRoom([numberOfBeds: -10]), createTypeOfRoom([numberOfPeople: -10])]
    }

    @Unroll
    def "Admin can't add a room with empty photoUrl"() {
        when: "User adds the room with invalid values"
        roomFacade.addTypeOfRoom(typeOfRoom)
        then: "Room is not added"
        thrown(InvalidValuesException)
        where:
        typeOfRoom << [createTypeOfRoom([photoUrl: null]),createTypeOfRoom([photoUrl: ""])]
    }
}
