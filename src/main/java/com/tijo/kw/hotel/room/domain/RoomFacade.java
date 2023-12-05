package com.tijo.kw.hotel.room.domain;

import com.tijo.kw.hotel.room.dto.RoomDto;
import com.tijo.kw.hotel.room.dto.TypeOfRoomDto;
import com.tijo.kw.hotel.room.entity.Room;
import com.tijo.kw.hotel.room.entity.TypeOfRoom;
import com.tijo.kw.hotel.room.exception.DuplicateNumberException;
import com.tijo.kw.hotel.room.exception.InvalidValuesException;
import com.tijo.kw.hotel.room.exception.RoomTypeNotExistingException;
import com.tijo.kw.hotel.room.repository.RoomRepository;
import com.tijo.kw.hotel.room.repository.TypeOfRoomRepository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class RoomFacade {

    RoomRepository roomRepository;
    TypeOfRoomRepository typeOfRoomRepository;

    public RoomFacade(RoomRepository roomRepository, TypeOfRoomRepository typeOfRoomRepository) {
        this.roomRepository = roomRepository;
        this.typeOfRoomRepository = typeOfRoomRepository;
    }

    public RoomDto addRoom(RoomDto newRoom) {

        newRoom.validate();

        if (ifRoomExistsByNumber(newRoom.getNumber())) {
            throw new DuplicateNumberException("Romm with same number already exists");
        }

        if (!typeOfRoomRepository.existsById(newRoom.getTypeId())){
            throw new RoomTypeNotExistingException("Room type with given id doesn't exist");
        }

        Room roomToSave = newRoom.toEntity();
        roomRepository.save(roomToSave);

        return newRoom;
    }

    public boolean deleteRoom(UUID roomId) {
        return true;
    }

    public TypeOfRoomDto addTypeOfRoom(TypeOfRoomDto newTypeOfRoom) {

        newTypeOfRoom.validate();

        TypeOfRoom typeToSave = newTypeOfRoom.toEntity();
        typeOfRoomRepository.save(typeToSave);

        return newTypeOfRoom;
    }

    public boolean deleteTypeOfRoom(UUID typeOfRoomId) {
        return true;
    }

    public List<TypeOfRoomDto> getTypesOfRoom() {
        return typeOfRoomRepository.findAll().stream().map(TypeOfRoomDto::fromEntity).collect(Collectors.toList());
    }

    public TypeOfRoomDto getTypeOfRoom(UUID typeOfRoomId) {
        return TypeOfRoomDto.builder().build();
    }

    private boolean ifRoomExistsByNumber(int roomNumber) {
        return roomRepository.existsByNumber(roomNumber);
    }
}
