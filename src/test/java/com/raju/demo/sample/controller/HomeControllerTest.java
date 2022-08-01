package com.raju.demo.sample.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

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