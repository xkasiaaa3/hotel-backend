package com.tijo.kw.hotel.user.dto;

import com.tijo.kw.hotel.user.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserDetailsDto {
  UUID id;
  String firstName;
  String lastName;
  String email;
  Role role;
}
