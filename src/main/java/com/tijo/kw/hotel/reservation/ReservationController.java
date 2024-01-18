package com.tijo.kw.hotel.reservation;

import com.tijo.kw.hotel.reservation.domain.ReservationFacade;
import com.tijo.kw.hotel.reservation.dto.FullReservationDto;
import com.tijo.kw.hotel.reservation.dto.MakeReservationDto;
import com.tijo.kw.hotel.reservation.dto.ReservationDto;
import com.tijo.kw.hotel.reservation.dto.ReservationRangeDto;
import com.tijo.kw.hotel.room.dto.RoomDto;
import com.tijo.kw.hotel.room.dto.TypeOfRoomDto;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/reservation")
public class ReservationController {

    ReservationFacade reservationFacade;

    public ReservationController(ReservationFacade reservationFacade) {
        this.reservationFacade = reservationFacade;
    }

    @PostMapping(value = "/")
    @Operation(summary = "Makes reservation and returns full reservation")
    public ResponseEntity<ReservationDto> addReservation(@RequestBody MakeReservationDto makeReservation) {
        return ResponseEntity.ok(reservationFacade.makeReservation(makeReservation));
    }


    @GetMapping(value = "/all")
    @Operation(summary = "Return list of all made reservations")
    public ResponseEntity<List<FullReservationDto>> getReservations() {
        return ResponseEntity.ok(reservationFacade.getReservations());
    }

    @PostMapping(value = "/available")
    @Operation(summary = "Returns list of types of room' ids that have available rooms in given reservation range")
    public ResponseEntity<List<UUID>> getAvailableTypesOfRoomIds(@RequestBody ReservationRangeDto reservationRange) {
        return ResponseEntity.ok(reservationFacade.getAvailableTypesOfRoomIds(reservationRange));
    }

    @GetMapping(value = "")
    @Operation(summary = "Returns list of types of room' that haveat least one room")
    public ResponseEntity<List<TypeOfRoomDto>> getTypesOfRoomWithRooms() {
        return ResponseEntity.ok(reservationFacade.getTypesOfRoomWithRooms());
    }

    @Hidden
    @PostMapping("/cleanup")
    public ResponseEntity<Boolean> cleanup() {
        reservationFacade.cleanup();
        return ResponseEntity.ok(true);
    }
}
