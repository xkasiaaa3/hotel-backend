package com.tijo.kw.hotel

import com.tijo.kw.hotel.reservation.domain.ReservationFacade
import com.tijo.kw.hotel.reservation.repository.ReservationRepository
import com.tijo.kw.hotel.room.domain.RoomFacade
import com.tijo.kw.hotel.room.dto.RoomDto
import com.tijo.kw.hotel.room.dto.TypeOfRoomDto
import com.tijo.kw.hotel.samples.ReservationSample
import com.tijo.kw.hotel.samples.RoomSample
import com.tijo.kw.hotel.samples.TypeOfRoomSample
import com.tijo.kw.hotel.security.auth.AuthenticationService
import spock.lang.Specification

class ReservationSpec extends Specification implements RoomSample, TypeOfRoomSample, ReservationSample {

    RoomFacade roomFacade = Stub(RoomFacade)
    AuthenticationService authenticationService = Mock(AuthenticationService)

    ReservationRepository reservationRepository = new InMemoryReservationRepository();

    ReservationFacade reservationFacade = new ReservationFacade(reservationRepository, roomFacade, authenticationService);

    TypeOfRoomDto type = createTypeOfRoom()
    RoomDto room = createRoom()

    def setup() {
        given: "There is a type of room #type"
        roomFacade.getTypesOfRoom() >> [type]
        roomFacade.getTypeOfRoom(ROOM_TYPE_ID) >> type
        and: "There is a room #room with type #type"
        roomFacade.getRooms() >> [room]
        roomFacade.getRoom(ROOM_ID) >> room

    }

    def "New type of room with room should be available"() {
        expect: "The type of room with added room is shown"
        reservationFacade.getTypesOfRoomWithRooms() == [createTypeOfRoom()]
        and: "The type of room with added room is available for reservation"
        reservationFacade.getAvailableTypesOfRoomIds(createReservationRange()) == [ROOM_TYPE_ID]

    }

    def "Type of room without room shouldn't be available"() {
        when: "There is new type #newType without any room"
        TypeOfRoomDto newType = createTypeOfRoom([id: UUID.randomUUID()])
        roomFacade.getTypesOfRoom() >> [type, newType]
        roomFacade.getTypeOfRoom(newType.getId()) >> newType
        and: "Only type of room with room is shown"
        reservationFacade.getTypesOfRoomWithRooms() == [createTypeOfRoom()]
        then:"Only type of room with room is available for reservation"
        reservationFacade.getAvailableTypesOfRoomIds(createReservationRange()) == [ROOM_TYPE_ID]
    }


    def "User should make reservation when there is room available"() {
        when: "User tries to make reservation on the only type of room available with just one room"
        def reservation = reservationFacade.makeReservation(createReservationRequest())
        then: "Reservation is made"
        reservation == createReservation([id: reservation.getId(), roomId: ROOM_ID])
        and: "There is no types of room available for the same date range"
        reservationFacade.getAvailableTypesOfRoomIds(createReservationRange()).isEmpty()

    }

    def "There shouldn't be rooms available after making reservation"() {
        given: "The reservation on the only room from #START_DATE to #END_DATE is made "
        reservationFacade.makeReservation(createReservationRequest())
        when: "User wants to make reservation on overlapping dates (from #RESERVATION_START_DATE to #RESERVATION_END_DATE)"
        List<UUID> availableTypes = reservationFacade.getAvailableTypesOfRoomIds(createReservationRange([startDate: RESERVATION_START_DATE, endDate: RESERVATION_END_DATE]))
        then: "There are no available types of room"
        availableTypes.isEmpty()

        where:
        RESERVATION_START_DATE  | RESERVATION_END_DATE
        START_DATE              | END_DATE
        START_DATE.plusDays(1)  | END_DATE
        START_DATE              | END_DATE.minusDays(1)
        START_DATE.plusDays(1)  | END_DATE.plusDays(1)
        START_DATE.minusDays(1) | END_DATE.minusDays(1)
        START_DATE.minusDays(1) | END_DATE.plusDays(1)
        START_DATE.plusDays(1)  | END_DATE.minusDays(1)
    }

}
