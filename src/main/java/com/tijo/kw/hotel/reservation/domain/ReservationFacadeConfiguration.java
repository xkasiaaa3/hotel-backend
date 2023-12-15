package com.tijo.kw.hotel.reservation.domain;

import com.tijo.kw.hotel.reservation.repository.ReservationRepository;
import com.tijo.kw.hotel.room.domain.RoomFacade;
import com.tijo.kw.hotel.security.auth.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservationFacadeConfiguration {

    ReservationRepository reservationRepository;
    RoomFacade roomFacade;
    AuthenticationService authenticationService;

    @Autowired
    public ReservationFacadeConfiguration(ReservationRepository reservationRepository, RoomFacade roomFacade, AuthenticationService authenticationService) {
        this.reservationRepository = reservationRepository;
        this.roomFacade = roomFacade;
        this.authenticationService = authenticationService;
    }

    @Bean
    ReservationFacade reservationFacade(){
        return new ReservationFacade(reservationRepository, roomFacade,authenticationService);
    }
}
