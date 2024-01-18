package com.tijo.kw.hotel.security.auth;

import lombok.*;
import lombok.experimental.FieldDefaults;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {
  String name;
  String surname;
  String email;
  String password;
}
