package com.tijo.kw.hotel.room;

import com.tijo.kw.hotel.room.domain.RoomFacade;
import com.tijo.kw.hotel.room.dto.RoomDto;
import com.tijo.kw.hotel.room.dto.TypeOfRoomDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(value="/api/room")
public class RoomController {

    private RoomFacade roomFacade;

    @PostMapping(value ="")
    public ResponseEntity<RoomDto> addRoom(@RequestBody RoomDto room){
        return ResponseEntity.ok(roomFacade.addRoom(room));
    }

    @PostMapping(value ="/type")
    public ResponseEntity<TypeOfRoomDto> addTypeOfRoom(@RequestBody TypeOfRoomDto typeOfRoom){
        return ResponseEntity.ok(roomFacade.addTypeOfRoom(typeOfRoom));
    }

    @DeleteMapping(value ="/{roomId}")
    public ResponseEntity<Boolean> deleteRoom(@PathVariable UUID roomId){
        return ResponseEntity.ok(roomFacade.deleteRoom(roomId));
    }

    @GetMapping
    public ResponseEntity<List<TypeOfRoomDto>> getTypesOfRoom(){
        return ResponseEntity.ok(roomFacade.getTypesOfRoom());
    }

    @GetMapping(value="/{typeOfRoomId}")
    public ResponseEntity<TypeOfRoomDto> getTypeOfRoom(@PathVariable UUID typeOfRoomId){
        return ResponseEntity.ok(roomFacade.getTypeOfRoom(typeOfRoomId));
    }

}
