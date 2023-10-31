package com.tijo.kw.hotel

import com.tijo.kw.hotel.room.domain.RoomFacade
import com.tijo.kw.hotel.room.dto.RoomDto
import com.tijo.kw.hotel.samples.RoomSample
import spock.lang.Specification

class RoomSpec extends Specification implements RoomSample {

    RoomFacade roomFacade;

    def "Admin can add a room with right values"() {
        given: "The logged user has role ADMIN"
        and: "The type of room is added before"
        when: "User adds the room with right values"
        RoomDto newRoom = roomFacade.addRoom(createRoom())
        then: "Room is added"
        newRoom == createRoom()
    }

    def "Admin can't add a room with empty values"() {
        given: "The logged user has role ADMIN"
        and: "The type of room is added before"
        when: "User adds the room with empty values"
        roomFacade.addRoom(createRoom())
        then: "Room is not added"
    }

    def "Admin can't add a room if there is a room with the same id"() {
        given: "The logged user has role ADMIN"
        and: "The type of room is added before"
        and: "THe room with the same id is added before"
        when: "User adds the room with the same id"
        then: "Room is not added"
        roomFacade.addRoom(createRoom())
    }

    def "Admin can't add a room if there is a room with the same room number"() {
        given: "The logged user has role ADMIN"
        and: "The type of room is added before"
        and: "THe room with the same room number is added before"
        when: "User adds the room with the same room number"
        then: "Room is not added"
        roomFacade.addRoom(createRoom())
    }

    def "Admin can't add a room if the type of said room is not existing"() {
        given: "The logged user has role ADMIN"
        when: "User adds the room with not existing type of room"
        then: "Room is not added"
        roomFacade.addRoom(createRoom())
    }
}
