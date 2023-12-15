package com.tijo.kw.hotel.integration

import com.fasterxml.jackson.databind.ObjectMapper
import com.tijo.kw.hotel.samples.UserSample
import com.tijo.kw.hotel.security.auth.AuthenticationRequest
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
class AuthIntegrationSpec extends Specification implements UserSample{
    @Autowired
    private WebApplicationContext context

    private MockMvc mockMvc;

    private ObjectMapper om = new ObjectMapper();

    def setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
     //   mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register").content(om.writeValueAsString(REGISTER_REQUEST)))
    }

    def "Guest should register on new email"() {
        when: "Guest register on new email"
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(REGISTER_REQUEST))).andReturn()
        then: "Guest is registered"
        result.getResponse().status == 200
    }

    def "Guest shouldn't register on old email"() {
        given: "Guest register on new email"
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(REGISTER_REQUEST)))
        when: "Guest register on the existing email"
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(REGISTER_REQUEST))).andReturn()
        then: "Guest isn't registered"
        result.getResponse().status != 200
    }

    def "Guest should log in on existing account with good credentials"() {
        given: "Guest register"
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(REGISTER_REQUEST)))
        when: "Guest log in with good credentials"
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/authenticate").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(AUTHENTICATION_REQUEST))).andReturn()
        then: "Guest is logged in"
        result.getResponse().status == 200
    }

    def "Guest shouldn't log in on existing account with bad credentials"() {
        given: "Guest register"
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(REGISTER_REQUEST)))
        when: "Guest log in with bad credentials"
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/authenticate").contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsString(AuthenticationRequest.builder().email(USER_EMAIL).password("123").build()))).andReturn()
        then: "Guest isn't logged in"
        result.getResponse().status != 200
    }
}
