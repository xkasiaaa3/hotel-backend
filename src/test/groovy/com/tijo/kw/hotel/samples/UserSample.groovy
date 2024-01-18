package com.tijo.kw.hotel.samples


import com.tijo.kw.hotel.security.auth.AuthenticationRequest
import com.tijo.kw.hotel.security.auth.RegisterRequest

trait UserSample {

    static UUID USER_ID = UUID.randomUUID()

    String USER_EMAIL = "jk@fake.pl"
    String USER_PASSWORD = "qwert12345"


    RegisterRequest REGISTER_REQUEST = RegisterRequest.builder()
            .name("Jan")
            .surname("Kowalski")
            .email(USER_EMAIL)
            .password(USER_PASSWORD)
            .build()

    AuthenticationRequest AUTHENTICATION_REQUEST = AuthenticationRequest.builder()
            .email(USER_EMAIL)
            .password(USER_PASSWORD)
            .build()

}
