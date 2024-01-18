package com.tijo.kw.hotel.room.domain;

import com.tijo.kw.hotel.reservation.dto.ReservationRangeDto;
import com.tijo.kw.hotel.room.dto.*;
import com.tijo.kw.hotel.room.entity.Room;
import com.tijo.kw.hotel.room.entity.TypeOfRoom;
import com.tijo.kw.hotel.room.exception.DuplicateNumberException;
import com.tijo.kw.hotel.room.exception.RoomTypeNotExistingException;
import com.tijo.kw.hotel.room.repository.RoomRepository;
import com.tijo.kw.hotel.room.repository.TypeOfRoomRepository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.tijo.kw.hotel.room.dto.RoomWithTypeDto.makeRoomWithType;

public class RoomFacade {

    RoomRepository roomRepository;
    TypeOfRoomRepository typeOfRoomRepository;

    public RoomFacade(RoomRepository roomRepository, TypeOfRoomRepository typeOfRoomRepository) {
        this.roomRepository = roomRepository;
        this.typeOfRoomRepository = typeOfRoomRepository;
    }

    public RoomDto addRoom(MakeRoomDto makeRoom) {

        RoomDto newRoom = makeRoom.toRoomDto();
        newRoom.validate();

        if (ifRoomExistsByNumber(newRoom.getNumber())) {
            throw new DuplicateNumberException("Romm with same number already exists");
        }

        if (!typeOfRoomRepository.existsById(newRoom.getTypeId())) {
            throw new RoomTypeNotExistingException("Room type with given id doesn't exist");
        }

        Room roomToSave = newRoom.toEntity();
        roomRepository.save(roomToSave);

        return newRoom;
    }

    public List<RoomDto> getRooms() {
        return roomRepository.findAll().stream().map(RoomDto::fromEntity).collect(Collectors.toList());
    }


    public List<RoomWithTypeDto> getRoomsWithType() {
        return roomRepository.findAll().stream().map(RoomDto::fromEntity).map(room -> makeRoomWithType(room, getTypeOfRoom(room.getTypeId()))).collect(Collectors.toList());
    }

    public boolean deleteRoom(UUID roomId) {

        if (!roomRepository.existsById(roomId)) {
            return false;
        }

        roomRepository.deleteById(roomId);
        return true;
    }

    public TypeOfRoomDto addTypeOfRoom(MakeTypeOfRoomDto makeTypeOfRoom) {

        TypeOfRoomDto newTypeOfRoom = makeTypeOfRoom.toTypeOfRoom();

        newTypeOfRoom.validate();

        TypeOfRoom typeToSave = newTypeOfRoom.toEntity();
        typeOfRoomRepository.save(typeToSave);

        return newTypeOfRoom;
    }

    public boolean deleteTypeOfRoom(UUID typeOfRoomId) {
        if (!typeOfRoomRepository.existsById(typeOfRoomId)) {
            return false;
        }

        typeOfRoomRepository.deleteById(typeOfRoomId);
        return true;
    }

    public List<TypeOfRoomDto> getTypesOfRoom() {
        return typeOfRoomRepository.findAll().stream().map(TypeOfRoomDto::fromEntity).collect(Collectors.toList());
    }

    public TypeOfRoomDto getTypeOfRoom(UUID typeOfRoomId) {

        TypeOfRoom typeOfRoom = typeOfRoomRepository.findById(typeOfRoomId).orElseThrow(() -> new RoomTypeNotExistingException("Room type with given id doesn't exist"));

        return TypeOfRoomDto.fromEntity(typeOfRoom);
    }

    public RoomDto getRoom(UUID roomId) {

        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RoomTypeNotExistingException("Room type with given id doesn't exist"));

        return RoomDto.fromEntity(room);
    }

    private boolean ifRoomExistsByNumber(int roomNumber) {
        return roomRepository.existsByNumber(roomNumber);
    }

    public void cleanup() {
        roomRepository.deleteAll();
        typeOfRoomRepository.deleteAll();
    }
}
