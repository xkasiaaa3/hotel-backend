package com.tijo.kw.hotel

import com.tijo.kw.hotel.reservation.domain.ReservationFacade
import com.tijo.kw.hotel.reservation.domain.ReservationFacadeConfiguration
import com.tijo.kw.hotel.reservation.dto.MakeReservationDto
import com.tijo.kw.hotel.reservation.dto.ReservationRangeDto
import com.tijo.kw.hotel.reservation.repository.ReservationRepository
import com.tijo.kw.hotel.room.domain.RoomFacade
import com.tijo.kw.hotel.room.dto.TypeOfRoomDto
import com.tijo.kw.hotel.room.entity.Room
import com.tijo.kw.hotel.room.exception.RoomTypeNotExistingException
import com.tijo.kw.hotel.room.repository.RoomRepository
import com.tijo.kw.hotel.room.repository.TypeOfRoomRepository
import com.tijo.kw.hotel.samples.ReservationSample
import com.tijo.kw.hotel.samples.RoomSample
import com.tijo.kw.hotel.samples.TypeOfRoomSample
import com.tijo.kw.hotel.security.auth.AuthenticationService
import org.springframework.boot.test.mock.mockito.MockBean
import spock.lang.Specification

class ReservationSpec extends Specification implements RoomSample, TypeOfRoomSample, ReservationSample {

    RoomRepository roomRepository = new InMemoryRoomRepository();
    TypeOfRoomRepository typeOfRoomRepository = new InMemoryTypeOfRoomRepository();
    RoomFacade roomFacade = new RoomFacade(roomRepository, typeOfRoomRepository);

    ReservationRepository reservationRepository = new InMemoryReservationRepository();

    ReservationFacade reservationFacade = new ReservationFacade(reservationRepository, roomFacade, Mock(AuthenticationService));


    def setup() {
        roomFacade.addTypeOfRoom(createTypeOfRoom())
        roomFacade.addRoom(createRoom())
    }

    def "New room should be available"() {
        expect:
        reservationFacade.getAvailableTypesOfRoomIds(new ReservationRangeDto(START_DATE, END_DATE)) == [ROOM_TYPE_ID]
    }

    def "User should make reservation of existing room"() {
        when:
        def reservation = reservationFacade.makeReservation(MakeReservationDto.builder()
                .allInclusive(true)
                .userId(USER_ID)
                .typeOfRoomId(ROOM_TYPE_ID)
                .reservationRange(new ReservationRangeDto(START_DATE, END_DATE))
                .build())
        then:
        reservationFacade.getAvailableTypesOfRoomIds(new ReservationRangeDto(START_DATE, END_DATE)) == []
        and:
        reservation == createReservation([id: reservation.getId()])

    }

    def "User shouldn't make reservation of type of room without any rooms"() {
        when:
        reservationFacade.makeReservation(MakeReservationDto.builder()
                .allInclusive(true)
                .userId(USER_ID)
                .typeOfRoomId(UUID.randomUUID())
                .reservationRange(new ReservationRangeDto(START_DATE, END_DATE))
                .build())
        then:
        thrown(RuntimeException)
    }

    def "There shouldn't be rooms available after making reservation"() {
        given: "The reservation on the only room is made"
        reservationFacade.makeReservation(MakeReservationDto.builder()
                .allInclusive(true)
                .userId(USER_ID)
                .typeOfRoomId(ROOM_TYPE_ID)
                .reservationRange(new ReservationRangeDto(START_DATE, END_DATE))
                .build())
        when: "User wants to make reservation on overlappng dates"
        List<UUID> types = reservationFacade.getAvailableTypesOfRoomIds(new ReservationRangeDto(RESERVATION_START_DATE, RESERVATION_END_DATE))
        then: "There are no available types of room"
        types.isEmpty()

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
