package com.tijo.kw.hotel.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class UserDto {

    UUID id;
    String name;
    String surname;
    String email;
    String phoneNumber;
    String role;
}
