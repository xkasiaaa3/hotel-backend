package com.tijo.kw.hotel.security.auth;

import lombok.*;
import lombok.experimental.FieldDefaults;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterRequest {
  String firstName;
  String lastName;
  String email;
  String password;
}
