package com.tijo.kw.hotel.repository;

import com.tijo.kw.hotel.entity.TypeOfRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TypeOfRoomRepository extends JpaRepository<TypeOfRoom, UUID> {
}
