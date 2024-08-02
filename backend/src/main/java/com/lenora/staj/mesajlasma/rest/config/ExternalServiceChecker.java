package com.lenora.staj.mesajlasma.rest.config;


import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalServiceChecker {

    private final RestTemplate restTemplate;
    private final String keycloakUserInfo = "http://localhost:8080/realms/mesajlasma/protocol/openid-connect/userinfo";

    public ExternalServiceChecker() {
        this.restTemplate = new RestTemplate();
    }

    public boolean isServiceAvailable(String token) {



        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token); // Boşluk eklendi
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(keycloakUserInfo, HttpMethod.GET, entity, String.class);
            HttpStatusCode status = response.getStatusCode(); // HttpStatus kullanımı
            System.out.println(status);
            return status == HttpStatus.OK;
        } catch (Exception e) {
            return false;
        }
    }
}

