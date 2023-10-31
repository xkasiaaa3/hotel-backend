package com.tijo.kw.hotel.samples

import com.tijo.kw.hotel.dto.UserDto

trait UserSample {

    def USER_ID = UUID.randomUUID()

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

}
