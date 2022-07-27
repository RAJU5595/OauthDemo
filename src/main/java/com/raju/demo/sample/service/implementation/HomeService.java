package com.raju.demo.sample.service.implementation;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HomeService {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${date.api}")
    private String dateAPI;

    @Value("${weather.api}")
    private String weatherAPI;

    public ObjectNode getTheDate() throws Exception {
        try{
            ResponseEntity<ObjectNode> response = restTemplate.getForEntity(dateAPI,ObjectNode.class);
            ObjectNode jsonObject = response.getBody();
            return jsonObject;
        }
        catch (Exception e){
            throw new Exception("Something went wrong while calling the Date API");
        }
    }

    public ObjectNode getWeatherDetails(String city) throws Exception {
        try{
            city = String.join("+",city.split(" "));
            String weatherURL = weatherAPI+city;
            ResponseEntity<ObjectNode> response = restTemplate.getForEntity(weatherURL,ObjectNode.class);
            ObjectNode jsonObject = response.getBody();
            return jsonObject;
        }
        catch (Exception e){
            throw new Exception("Something went wrong while calling the Weather API");
        }
    }
}
