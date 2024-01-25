package com.tijo.kw.hotel.reservation;

import com.tijo.kw.hotel.reservation.domain.ReservationFacade;
import com.tijo.kw.hotel.reservation.dto.ReservationRangeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
@CrossOrigin(origins = "http://localhost:3000")
//@RequestMapping(value = "/api/reservation")
public class WebSocketController {

    ReservationFacade reservationFacade;

    public WebSocketController(ReservationFacade reservationFacade) {
        this.reservationFacade = reservationFacade;
    }

    @MessageMapping("/message")
    @SendTo("/topic/availableRooms")
    public List<UUID> getAvailableTypesOfRoomIds(ReservationRangeDto reservationRange) {
log.info("Jestem!");
        return reservationFacade.getAvailableTypesOfRoomIds(reservationRange);
    }
}
