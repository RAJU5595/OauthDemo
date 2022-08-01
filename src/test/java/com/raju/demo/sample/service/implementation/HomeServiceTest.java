package com.raju.demo.sample.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class HomeServiceTest {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private HomeService homeService;

    @Test
    void getTheDate() throws Exception {
        assertNotNull(homeService.getTheDate());
    }

    @Test
    void getWeatherDetails() throws Exception {
        assertNotNull(homeService.getWeatherDetails("Hyderabad"));
    }
}