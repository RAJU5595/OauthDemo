package com.raju.demo.sample.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.raju.demo.sample.service.implementation.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/date")
    @RolesAllowed({"user","admin"})
    public ObjectNode getDate() throws Exception {
        return homeService.getTheDate();
    }

    @GetMapping("/weather")
    @RolesAllowed({"user","admin"})
    public ObjectNode getWeather(@RequestParam(value = "city",defaultValue = "Bangalore") String city) throws Exception {
        return homeService.getWeatherDetails(city);
    }

}
