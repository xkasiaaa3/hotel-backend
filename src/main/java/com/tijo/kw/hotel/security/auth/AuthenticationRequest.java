package com.tijo.kw.hotel.security.auth;

import lombok.*;
import lombok.experimental.FieldDefaults;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {
 String email;
 String password;
}
