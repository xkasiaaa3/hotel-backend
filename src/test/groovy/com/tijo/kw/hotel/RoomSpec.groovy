package com.tijo.kw.hotel

import com.tijo.kw.hotel.room.domain.RoomFacade
import com.tijo.kw.hotel.room.dto.RoomDto
import com.tijo.kw.hotel.room.exception.DuplicateNumberException
import com.tijo.kw.hotel.room.exception.InvalidValuesException
import com.tijo.kw.hotel.room.exception.RoomTypeNotExistingException
import com.tijo.kw.hotel.room.repository.RoomRepository
import com.tijo.kw.hotel.room.repository.TypeOfRoomRepository
import com.tijo.kw.hotel.samples.RoomSample
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class RoomSpec extends Specification implements RoomSample {

    RoomRepository roomRepository = new InMemoryRoomRepository();
    TypeOfRoomRepository typeOfRoomRepository = new InMemoryTypeOfRoomRepository();
    RoomFacade roomFacade = new RoomFacade(roomRepository, typeOfRoomRepository);

    @Shared
    UUID typeId;

    def setup() {
        given: "Type of room with id #typeId exists"
        typeId = roomFacade.addTypeOfRoom(createTypeOfRoomRequest()).getId()
    }

    def "Admin can add a room with right values"() {
        when: "User adds the room with right values"
        RoomDto newRoom = roomFacade.addRoom(createRoomRequest([typeId: typeId]))
        then: "Room is added"
        newRoom == createRoom([typeId: typeId, id: newRoom.getId()])
    }

    @Unroll
    def "Admin can't add a room with empty values"() {
        when: "User adds the room with empty values"
        roomFacade.addRoom(room)
        then: "Room is not added"
        thrown(InvalidValuesException)
        where:
        room << [createRoomRequest([floor: null, typeId: typeId]), createRoomRequest([number: null, typeId: typeId])]
    }

    @Unroll
    def "Admin can't add a room with number or floor below 0"() {
        when: "User adds the room with invalid values"
        roomFacade.addRoom(room)
        then: "Room is not added"
        thrown(InvalidValuesException)
        where:
        room << [createRoomRequest([number: -1, typeId: typeId]), createRoomRequest([floor: -1, typeId: typeId])]
    }

    def "Admin can't add a room if there is a room with the same room number"() {
        given: "The room is added"
        roomFacade.addRoom(createRoomRequest([typeId: typeId]))
        when: "User adds the room with the same room number"
        roomFacade.addRoom(createRoomRequest([typeId: typeId]))
        then: "Room is not added"
        thrown(DuplicateNumberException)
    }

    def "Admin can't add a room if the type of said room is not existing"() {
        when: "User adds the room with not existing type of room"
        roomFacade.addRoom(createRoomRequest(typeId: UUID.randomUUID()))
        then: "Room is not added"
        thrown(RoomTypeNotExistingException)
    }

    def "Admin can delete room if there are no reservations"() {
        given: "The room is added"
        UUID roomId = roomFacade.addRoom(createRoomRequest([typeId: typeId])).getId()
        when: "User deletes the room"
        def result = roomFacade.deleteRoom(roomId)
        then: "Room is deleted"
        result
    }

}
