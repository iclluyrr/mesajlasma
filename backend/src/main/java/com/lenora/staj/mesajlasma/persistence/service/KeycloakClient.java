package com.lenora.staj.mesajlasma.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class KeycloakClient {
    private final WebClient client;

    @Autowired
    public KeycloakClient(@Value("${spring.security.oauth2.client.provider.keycloak.issuer-uri}") String keycloakUri) {
        client = WebClient.create(keycloakUri);
    }


    public Mono<String> login(String username, String password) {

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "password");
        formData.add("client_id", "spring-server");
        formData.add("username", username);
        formData.add("password", password);

        return client.post()
                .uri("/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .onStatus(HttpStatusCode::isError, resp -> {
                    return Mono.error(new LoginFailedException());
                })
                .bodyToMono(String.class);

    }

    public Mono<String> register(String username, String password) {

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("username", username);
        formData.add("password", password);

        return client.post()
                .uri("/auth/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .onStatus(HttpStatusCode::isError, resp -> Mono.error(new RegistrationFailedException()))
                .bodyToMono(String.class);
    }

    private String getAdminToken() {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "password");
        formData.add("client_id", "admin-cli");
        formData.add("username", "admin");
        formData.add("password", "admin");

        return client.post()
                .uri("/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .onStatus(HttpStatusCode::isError, resp -> {
                    return Mono.error(new LoginFailedException());
                })
                .bodyToMono(String.class).block();
    }
}



