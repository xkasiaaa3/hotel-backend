package com.tijo.kw.hotel.security.auth;

import lombok.*;
import lombok.experimental.FieldDefaults;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {
  String token;
}
