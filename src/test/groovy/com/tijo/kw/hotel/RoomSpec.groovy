package com.tijo.kw.hotel

import com.tijo.kw.hotel.room.domain.RoomFacade
import com.tijo.kw.hotel.room.dto.RoomDto
import com.tijo.kw.hotel.room.dto.TypeOfRoomDto
import com.tijo.kw.hotel.room.exception.InvalidValuesException
import com.tijo.kw.hotel.room.repository.RoomRepository
import com.tijo.kw.hotel.room.repository.TypeOfRoomRepository
import com.tijo.kw.hotel.samples.RoomSample
import spock.lang.Specification
import spock.lang.Unroll

class RoomSpec extends Specification implements RoomSample {

    RoomRepository roomRepository = new InMemoryRoomRepository();
    TypeOfRoomRepository typeOfRoomRepository = new InMemoryTypeOfRoomRepository();
    RoomFacade roomFacade = new RoomFacade(roomRepository, typeOfRoomRepository);

    def setup() {
        roomFacade.addTypeOfRoom(createTypeOfRoom())
    }

    def "Admin can add a room with right values"() {
        when: "User adds the room with right values"
        RoomDto newRoom = roomFacade.addRoom(createRoom())
        then: "Room is added"
        newRoom == createRoom()
    }

    @Unroll
    def "Admin can't add a room with empty values"() {
        when: "User adds the room with empty values"
        roomFacade.addRoom(room)
        then: "Room is not added"
        thrown(InvalidValuesException)
        where:
        room << [createRoom([photoUrl: null]), createRoom([photoUrl: ""]), createRoom([floor: null]), createRoom([number: null])]
    }

    @Unroll
    def "Admin can't add a room with number or floor below 0"() {
        when: "User adds the room with invalid values"
        roomFacade.addRoom(room)
        then: "Room is not added"
        thrown(InvalidValuesException)
        where:
        room << [createRoom([number: -1]), createRoom([floor: -1])]
    }

    def "Admin can't add a room if there is a room with the same room number"() {
        given: "The room is added"
        roomFacade.addRoom(createRoom())
        when: "User adds the room with the same room number"
        roomFacade.addRoom(createRoom(id: UUID.randomUUID()))
        then: "Room is not added"
        thrown(InvalidValuesException)
    }

    def "Admin can't add a room if the type of said room is not existing"() {
        when: "User adds the room with not existing type of room"
        roomFacade.addRoom(createRoom(typeId: UUID.randomUUID()))
        then: "Room is not added"
        thrown(InvalidValuesException)
    }

    def "Admin can delete room if there are no reservations"() {
        given: "The room is added"
        roomFacade.addRoom(createRoom())
        when: "User deletes the room"
        def result = roomFacade.deleteRoom(ROOM_ID)
        then: "Room is deleted"
        result
    }

}
