package com.tijo.kw.hotel.reservation;

import com.tijo.kw.hotel.reservation.domain.ReservationFacade;
import com.tijo.kw.hotel.reservation.dto.MakeReservationDto;
import com.tijo.kw.hotel.reservation.dto.ReservationDto;
import com.tijo.kw.hotel.reservation.dto.ReservationRangeDto;
import com.tijo.kw.hotel.room.dto.RoomDto;
import com.tijo.kw.hotel.room.dto.TypeOfRoomDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/reservation")
public class ReservationController {

    ReservationFacade reservationFacade;

    @PostMapping(value = "")
    @Operation(summary = "Makes reservation and returns full reservation")
    public ResponseEntity<ReservationDto> addRoom(@RequestBody MakeReservationDto makeReservation) {
        return ResponseEntity.ok(reservationFacade.makeReservation(makeReservation));
    }

    @PostMapping(value = "/available")
    @Operation(summary = "Returns list of types of room that have available rooms in given reservation range")
    public ResponseEntity<List<TypeOfRoomDto>> getAvailableTypesOfRoom(@RequestBody ReservationRangeDto reservationRange) {
        return ResponseEntity.ok(reservationFacade.getAvailableTypesOfRoom(reservationRange));
    }
}
