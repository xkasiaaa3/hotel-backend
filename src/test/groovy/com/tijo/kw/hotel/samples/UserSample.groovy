package com.tijo.kw.hotel.samples

import com.tijo.kw.hotel.dto.UserDto
import com.tijo.kw.hotel.security.auth.AuthenticationRequest
import com.tijo.kw.hotel.security.auth.RegisterRequest

trait UserSample {

    static UUID USER_ID = UUID.randomUUID()

    private Map<String, Object> DEFAULT_USER = [
            id         : USER_ID,
            name       : "Han",
            surname    : "Solo",
            email      : "hansolo@example.com",
            phoneNumber: "123456789",
            role       : "user"
    ] as Map<String, Object>

    UserDto createUser(Map<String, Object> changes = [:]) {
        def result = DEFAULT_USER + changes
        UserDto.builder()
                .id(result.id as UUID)
                .name(result.name as String)
                .surname(result.surname as String)
                .email(result.email as String)
                .phoneNumber(result.phoneNumber as String)
                .role(result.role as String)
                .build()
    }

    String USER_EMAIL = "jk@fake.pl"
    String USER_PASSWORD = "qwert12345"


    RegisterRequest REGISTER_REQUEST = RegisterRequest.builder()
            .firstName("Jan")
            .lastName("Kowalski")
            .email(USER_EMAIL)
            .password(USER_PASSWORD)
            .build()

    AuthenticationRequest AUTHENTICATION_REQUEST = AuthenticationRequest.builder()
            .email(USER_EMAIL)
            .password(USER_PASSWORD)
            .build()

}
