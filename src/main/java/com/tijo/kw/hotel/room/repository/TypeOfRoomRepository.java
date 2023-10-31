package com.tijo.kw.hotel.room.repository;

import com.tijo.kw.hotel.room.entity.TypeOfRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TypeOfRoomRepository extends JpaRepository<TypeOfRoom, UUID> {
}
