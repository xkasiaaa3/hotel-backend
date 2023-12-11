package com.tijo.kw.hotel.room.domain;

import com.tijo.kw.hotel.room.repository.RoomRepository;
import com.tijo.kw.hotel.room.repository.TypeOfRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoomFacadeConfiguration {

    RoomRepository roomRepository;
    TypeOfRoomRepository typeOfRoomRepository;

    @Autowired
    public RoomFacadeConfiguration(RoomRepository roomRepository, TypeOfRoomRepository typeOfRoomRepository) {
        this.roomRepository = roomRepository;
        this.typeOfRoomRepository = typeOfRoomRepository;
    }

    @Bean
    public RoomFacade roomFacade() {
        return new RoomFacade(roomRepository, typeOfRoomRepository);
    }
}
