package com.tijo.kw.hotel.reservation.domain;

import com.tijo.kw.hotel.reservation.repository.ReservationRepository;
import com.tijo.kw.hotel.room.domain.RoomFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservationFacadeConfiguration {

    ReservationRepository reservationRepository;
    RoomFacade roomFacade;

    @Autowired
    public ReservationFacadeConfiguration(ReservationRepository reservationRepository, RoomFacade roomFacade) {
        this.reservationRepository = reservationRepository;
        this.roomFacade = roomFacade;
    }

    @Bean
    ReservationFacade reservationFacade(){
        return new ReservationFacade(reservationRepository, roomFacade);
    }
}
