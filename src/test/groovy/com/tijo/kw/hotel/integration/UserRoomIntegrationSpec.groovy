package com.tijo.kw.hotel.integration


import com.tijo.kw.hotel.room.dto.TypeOfRoomDto
import com.tijo.kw.hotel.samples.RoomSample
import com.tijo.kw.hotel.samples.UserSample
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

class UserRoomIntegrationSpec extends IntegrationSpec implements UserSample, RoomSample {
    def setup() {
        given: "There is user with role USER"
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(REGISTER_REQUEST)))
        and: "The user is logged in"
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/authenticate").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(AUTHENTICATION_REQUEST)))
    }

    def "Logged user should get list of types of room"() {
        when: "User gets list of types of room"
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/reservation")).andReturn()
        then: "The list is empty"
        List<TypeOfRoomDto> list = om.readValue(result.getResponse().getContentAsString(), List<TypeOfRoomDto>.class)
        list.isEmpty()
    }

    def "Logged user can't add new room"() {
        when: "User tries to add room"
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/room").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(createRoom()))).andReturn()
        then: "User can't add room and the status is 401"
        result.getResponse().status == 401
    }

    def "Logged user can't add new type of room"() {
        when: "User tries to add type of room"
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/room/type").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(createTypeOfRoom()))).andReturn()
        then: "User can't add room and the status is 401"
        result.getResponse().status == 401
    }
}
