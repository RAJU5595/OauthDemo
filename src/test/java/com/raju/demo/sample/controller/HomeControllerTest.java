package com.raju.demo.sample.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(value = HomeController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    @WithMockUser(roles = {"admin","user"})
    void getDate() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/home/date");
        MvcResult result = (MvcResult) mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200,result.getResponse().getStatus());
    }

    @Test
    @WithMockUser(roles = {"admin","user"})
    void getWeather() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/home/weather");
        MvcResult result = (MvcResult) mockMvc.perform(requestBuilder).andReturn();
        assertEquals(200,result.getResponse().getStatus());
    }
}