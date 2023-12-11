package com.tijo.kw.hotel.reservation.domain;

import com.tijo.kw.hotel.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservationFacadeConfiguration {

    ReservationRepository reservationRepository;

    @Autowired
    public ReservationFacadeConfiguration(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Bean
    ReservationFacade reservationFacade(){
        return new ReservationFacade(reservationRepository);
    }
}
