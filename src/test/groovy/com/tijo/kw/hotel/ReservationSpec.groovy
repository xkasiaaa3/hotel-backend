package com.tijo.kw.hotel

import com.tijo.kw.hotel.reservation.domain.ReservationFacade
import com.tijo.kw.hotel.reservation.domain.ReservationFacadeConfiguration
import com.tijo.kw.hotel.reservation.dto.MakeReservationDto
import com.tijo.kw.hotel.reservation.dto.ReservationRangeDto
import com.tijo.kw.hotel.reservation.repository.ReservationRepository
import com.tijo.kw.hotel.room.domain.RoomFacade
import com.tijo.kw.hotel.room.entity.Room
import com.tijo.kw.hotel.room.repository.RoomRepository
import com.tijo.kw.hotel.room.repository.TypeOfRoomRepository
import com.tijo.kw.hotel.samples.ReservationSample
import com.tijo.kw.hotel.samples.RoomSample
import com.tijo.kw.hotel.samples.TypeOfRoomSample
import org.springframework.boot.test.mock.mockito.MockBean
import spock.lang.Specification

class ReservationSpec extends Specification implements RoomSample, TypeOfRoomSample, ReservationSample {

    RoomRepository roomRepository = new InMemoryRoomRepository();
    TypeOfRoomRepository typeOfRoomRepository = new InMemoryTypeOfRoomRepository();
    RoomFacade roomFacade = new RoomFacade(roomRepository, typeOfRoomRepository);

    ReservationRepository reservationRepository = new InMemoryReservationRepository();

    ReservationFacade reservationFacade = new ReservationFacade(reservationRepository);


    def setup() {
        roomFacade.addTypeOfRoom(createTypeOfRoom())
        roomFacade.addRoom(createRoom())
    }

    def "User should make reservation of existing room"() {
        when:
        def reservation = reservationFacade.makeReservation(MakeReservationDto.builder()
                .allInclusive(true)
                .userId(USER_ID)
                .reservationRange(new ReservationRangeDto(START_DATE, END_DATE))
                .build())

        then:
        reservation == createReservation()
    }

}
