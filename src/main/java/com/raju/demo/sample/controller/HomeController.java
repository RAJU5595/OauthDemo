package com.raju.demo.sample.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    @GetMapping("/date")
    @RolesAllowed({"user","admin"})
    public ResponseEntity<Map<String,String>> getDate() throws URISyntaxException, IOException, InterruptedException {
        String url = "http://date.jsontest.com/";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,String> map = objectMapper.readValue(response.body(), Map.class);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @GetMapping("/weather")
    @RolesAllowed({"user","admin"})
    public ResponseEntity<Map<String,String>> getWeather() throws URISyntaxException, IOException, InterruptedException {
        String url = "http://api.weatherstack.com/current?access_key=1efaeb389c49f8dfb8003ebf3d954291&query=New%20York";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,String> map = objectMapper.readValue(response.body(), Map.class);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

}
