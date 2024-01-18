package com.tijo.kw.hotel.reservation.domain;

import com.tijo.kw.hotel.reservation.dto.FullReservationDto;
import com.tijo.kw.hotel.reservation.dto.MakeReservationDto;
import com.tijo.kw.hotel.reservation.dto.ReservationDto;
import com.tijo.kw.hotel.reservation.dto.ReservationRangeDto;
import com.tijo.kw.hotel.reservation.exception.NoAvailableRoom;
import com.tijo.kw.hotel.reservation.repository.ReservationRepository;
import com.tijo.kw.hotel.room.domain.RoomFacade;
import com.tijo.kw.hotel.room.dto.RoomDto;
import com.tijo.kw.hotel.room.dto.TypeOfRoomDto;
import com.tijo.kw.hotel.security.auth.AuthenticationService;
import com.tijo.kw.hotel.user.dto.UserDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.tijo.kw.hotel.reservation.dto.FullReservationDto.makeFullReservation;

public class ReservationFacade {

    ReservationRepository reservationRepository;

    RoomFacade roomFacade;
    AuthenticationService authenticationService;

    public ReservationFacade(ReservationRepository reservationRepository, RoomFacade roomFacade, AuthenticationService authenticationService) {
        this.reservationRepository = reservationRepository;
        this.roomFacade = roomFacade;
        this.authenticationService = authenticationService;
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
        List<RoomDto> rooms = getAvailableRooms(reservationRange);

        for (RoomDto room : rooms) {
            if (room.getTypeId().equals(typeOfRoomId)) {
                return room.getId();
            }
        }
        throw new NoAvailableRoom("There is no room available");
    }

    public List<UUID> getAvailableTypesOfRoomIds(ReservationRangeDto reservationRange) {

        List<RoomDto> rooms = getAvailableRooms(reservationRange);

        return rooms.stream().map(RoomDto::getTypeId).distinct().collect(Collectors.toList());

    }

    public List<RoomDto> getAvailableRooms(ReservationRangeDto reservationRange) {

        List<RoomDto> allRooms = roomFacade.getRooms();

        List<UUID> takenRoomsIds = reservationRepository.getRoomIdsNotAvailable(reservationRange.getStartDate(), reservationRange.getEndDate());

        return allRooms.stream().filter(room -> !takenRoomsIds.contains(room.getId())).collect(Collectors.toList());
    }

    public List<FullReservationDto> getReservations() {

        return reservationRepository.findAll().stream().map(ReservationDto::fromEntity)
                .map(this::buildFullReservation)
                .collect(Collectors.toList());
    }

    private FullReservationDto buildFullReservation(ReservationDto reservation) {

        UserDetailsDto user = authenticationService.getUserDetails(reservation.getUserId());
        RoomDto room = roomFacade.getRoom(reservation.getRoomId());
        TypeOfRoomDto type = roomFacade.getTypeOfRoom(room.getTypeId());

        return makeFullReservation(reservation, user, type, room);

    }


    public List<TypeOfRoomDto> getTypesOfRoomWithRooms() {

        Set<UUID> typesWithRoomsIds = roomFacade.getRooms().stream().map(RoomDto::getTypeId).collect(Collectors.toSet());

        List<TypeOfRoomDto> types = roomFacade.getTypesOfRoom();

        return types.stream().filter(t -> typesWithRoomsIds.contains(t.getId())).distinct().collect(Collectors.toList());
    }

    public void  cleanup(){
        reservationRepository.deleteAll();
    }
}
