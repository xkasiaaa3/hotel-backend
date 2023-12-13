package com.tijo.kw.hotel.room.dto;

import com.tijo.kw.hotel.room.entity.TypeOfRoom;
import com.tijo.kw.hotel.room.exception.InvalidValuesException;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
@EqualsAndHashCode
public class TypeOfRoomDto {

    UUID id;
    String name;
    Integer numberOfPeople;
    Integer numberOfBeds;
    Boolean bathroom;
    Boolean balcony;
    Double pricePerDay;
    String photoUrl;

    public TypeOfRoom toEntity(){
        return TypeOfRoom.builder()
                .id(id)
                .name(name)
                .numberOfBeds(numberOfBeds)
                .numberOfPeople(numberOfPeople)
                .balcony(balcony)
                .bathroom(bathroom)
                .pricePerDay(pricePerDay)
                .photoUrl(photoUrl)
                .build();
    }

    public static TypeOfRoomDto fromEntity(TypeOfRoom typeOfRoom){
        return TypeOfRoomDto.builder()
                .id(typeOfRoom.getId())
                .name(typeOfRoom.getName())
                .numberOfBeds(typeOfRoom.getNumberOfBeds())
                .numberOfPeople(typeOfRoom.getNumberOfPeople())
                .balcony(typeOfRoom.isBalcony())
                .bathroom(typeOfRoom.isBathroom())
                .pricePerDay(typeOfRoom.getPricePerDay())
                .photoUrl(typeOfRoom.getPhotoUrl())
                .build();
    }

    public void validate(){

        if (numberOfPeople < 1) {
            throw new InvalidValuesException("Number of people");
        }

        if (numberOfBeds < 1) {
            throw new InvalidValuesException("Number of people");
        }

        if (photoUrl == null || photoUrl == ""){
            throw new InvalidValuesException("Photo url");
        }
    }
}
