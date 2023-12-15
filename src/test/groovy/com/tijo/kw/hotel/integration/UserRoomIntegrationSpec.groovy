package com.tijo.kw.hotel.integration

import com.fasterxml.jackson.databind.ObjectMapper
import com.tijo.kw.hotel.room.dto.RoomDto
import com.tijo.kw.hotel.room.dto.TypeOfRoomDto
import com.tijo.kw.hotel.room.entity.Room
import com.tijo.kw.hotel.room.entity.TypeOfRoom
import com.tijo.kw.hotel.samples.RoomSample
import com.tijo.kw.hotel.samples.UserSample
import com.tijo.kw.hotel.security.auth.RegisterRequest
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification


@RunWith(SpringRunner.class)
@SpringBootTest
class UserRoomIntegrationSpec extends Specification implements UserSample, RoomSample {

    @Autowired
    private WebApplicationContext context

    private MockMvc mockMvc;

    private ObjectMapper om = new ObjectMapper();

    def setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(REGISTER_REQUEST)))
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/authenticate").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(AUTHENTICATION_REQUEST)))
    }

    def "Logged user should get list of types of room"() {
        when: "User gets list of types of room"
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/room")).andReturn()
        then: "The list is empty"
        List<TypeOfRoomDto> list = om.readValue(result.getResponse().getContentAsString(), List<TypeOfRoomDto>.class)
        list == []
    }

    def "Logged user can't add new room"() {
        when: "User tries to add room"
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/room").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(createRoom()))).andReturn()
        then: "User can't add room"
        result.getResponse().status != 200
    }

    def "Logged user can't add new type of room"() {
        when: "User tries to add type of room"
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/room/type").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(createTypeOfRoom()))).andReturn()
        then: "User can't add room"
        result.getResponse().status != 200
    }
}
