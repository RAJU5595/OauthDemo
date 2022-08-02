package com.raju.demo.sample.service.implementation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class HomeServiceTest {

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