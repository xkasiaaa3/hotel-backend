package com.tijo.kw.hotel.integration

import com.tijo.kw.hotel.configuration.PostgresContainerStarter
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.testcontainers.spock.Testcontainers
import spock.lang.Specification
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner.class)
@SpringBootTest
@Testcontainers
abstract class IntegrationSpec extends Specification {

    static {
        PostgresContainerStarter.init()
        System.setProperty("spring.datasource.url", PostgresContainerStarter.getPostgreSQLContainer().getJdbcUrl())
        System.setProperty("spring.datasource.username", PostgresContainerStarter.getPostgreSQLContainer().getUsername())
        System.setProperty("spring.datasource.password", PostgresContainerStarter.getPostgreSQLContainer().getPassword())
    }
    MockMvc mockMvc

    @Autowired
    private WebApplicationContext context

    @Autowired
    ObjectMapper om;

    def setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build()
    }

    def cleanup() {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/cleanup").contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/room/cleanup").contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/reservation/cleanup").contentType(MediaType.APPLICATION_JSON));
    }
}