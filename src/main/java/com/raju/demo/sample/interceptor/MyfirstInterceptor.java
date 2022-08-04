package com.raju.demo.sample.interceptor;


import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class MyfirstInterceptor implements HandlerInterceptor {

    private RestTemplate restTemplate = new RestTemplate();

    private String getBase64Credentials(){
        String plainCreds = "raju" + ":" + "raju";
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        return new String(base64CredsBytes);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("im in interceptor");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        String tokenEndpoint = "http://localhost:8080/realms/springdemo/protocol/openid-connect/token";
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("username","raju");
        map.add("password","raju");
        map.add("grant_type","password");
        map.add("client_id","springboot");

        HttpEntity<MultiValueMap<String, String>> tokenrequest = new HttpEntity<MultiValueMap<String, String>>(map,headers);
        ResponseEntity<ObjectNode> result = restTemplate.postForEntity( tokenEndpoint, tokenrequest , ObjectNode.class );
        response.addHeader("Authorization","Bearer "+result.getBody().get("access_token").asText());
        return true;
    }
}
