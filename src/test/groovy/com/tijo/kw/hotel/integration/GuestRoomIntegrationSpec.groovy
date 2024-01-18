package com.tijo.kw.hotel.integration

import com.tijo.kw.hotel.room.dto.TypeOfRoomDto
import com.tijo.kw.hotel.samples.RoomSample
import com.tijo.kw.hotel.samples.UserSample
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

class GuestRoomIntegrationSpec extends IntegrationSpec implements UserSample, RoomSample {

    def "Guest should get list of types of room"() {
        when: "Guest gets list of types of room"
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/reservation")).andReturn()
        then: "The list is empty"
        List<TypeOfRoomDto> list = om.readValue(result.getResponse().getContentAsString(), List<TypeOfRoomDto>.class)
        list.isEmpty()
    }

    def "Guest can't add new room"() {
        when: "Guest tries to add room"
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/room").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(createRoomRequest()))).andReturn()
        then: "Guest can't add room and the status is 401"
        result.getResponse().status == 401
    }

    def "Guest can't add new type of room"() {
        when: "Guest tries to add type of room"
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/room/type").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(createTypeOfRoomRequest()))).andReturn()
        then: "Guest can't add room and the status is 401"
        result.getResponse().status == 401
    }
}
