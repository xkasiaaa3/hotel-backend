package com.tijo.kw.hotel.reservation.domain;

import com.tijo.kw.hotel.reservation.dto.MakeReservationDto;
import com.tijo.kw.hotel.reservation.dto.ReservationDto;
import com.tijo.kw.hotel.reservation.dto.ReservationRangeDto;
import com.tijo.kw.hotel.reservation.repository.ReservationRepository;
import com.tijo.kw.hotel.room.domain.RoomFacade;
import com.tijo.kw.hotel.room.dto.RoomDto;
import com.tijo.kw.hotel.room.dto.TypeOfRoomDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ReservationFacade {

    ReservationRepository reservationRepository;

    RoomFacade roomFacade;

    public ReservationFacade(ReservationRepository reservationRepository, RoomFacade roomFacade) {
        this.reservationRepository = reservationRepository;
        this.roomFacade = roomFacade;
    }

    public ReservationDto makeReservation(MakeReservationDto makeReservation) {

        ReservationRangeDto reservationRange = makeReservation.getReservationRange();

        TypeOfRoomDto typeOfRoom = roomFacade.getTypeOfRoom(makeReservation.getTypeOfRoomId());

        UUID roomId = getAvailableRoom(makeReservation.getTypeOfRoomId(), makeReservation.getReservationRange());

        double totalPrice = typeOfRoom.getPricePerDay() * reservationRange.getDurationInDays();

        ReservationDto reservation =
                ReservationDto.builder()
                        .allInclusive(makeReservation.isAllInclusive())
                        .endDate(reservationRange.getEndDate())
                        .startDate(reservationRange.getStartDate())
                        .userId(makeReservation.getUserId())
                        .price(totalPrice)
                        .id(UUID.randomUUID())
                        .roomId(roomId)
                        .build();

        reservationRepository.save(reservation.toEntity());

        return reservation;


    }

    public UUID getAvailableRoom(UUID typeOfRoomId, ReservationRangeDto reservationRange) {
        RoomDto room = getAvailableRooms(reservationRange).stream().filter(r -> r.getTypeId() == typeOfRoomId).findFirst().orElseThrow();
        return room.getId();
    }

    public List<UUID> getAvailableTypesOfRoomIds(ReservationRangeDto reservationRange) {

        List<RoomDto> rooms = getAvailableRooms(reservationRange);

        return rooms.stream().map(r -> r.getTypeId()).distinct().collect(Collectors.toList());

    }

    public List<RoomDto> getAvailableRooms(ReservationRangeDto reservationRange) {

        List<RoomDto> allRooms = roomFacade.getRooms();

        List<UUID> takenRoomsIds = reservationRepository.getRoomIdsNotAvailable(reservationRange.getStartDate(), reservationRange.getEndDate());

        return allRooms.stream().filter(room -> !takenRoomsIds.contains(room.getId())).collect(Collectors.toList());
    }
}
