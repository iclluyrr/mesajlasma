package com.lenora.staj.mesajlasma.persistence.service;

import jakarta.annotation.PostConstruct;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
public class KeycloakClient {

    private Keycloak keycloak;

    @Value("${keycloak.auth-server-url}")
    private String serverUrl;

    @Value("${keycloak.admin-realm}")
    private String adminRealm;

    @Value("${keycloak.admin-username}")
    private String adminUsername;

    @Value("${keycloak.admin-password}")
    private String adminPassword;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    @PostConstruct
    public void init() {
        keycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(adminRealm)
                .clientId("admin-cli")
                .grantType(OAuth2Constants.PASSWORD)
                .username(adminUsername)
                .password(adminPassword)
                .build();
    }

    public Mono<String> login(String username, String password) {
        return Mono.fromCallable(()->{
        Keycloak loginKeycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm("mesajlasma")
                .clientId("spring-server")
                .grantType(OAuth2Constants.PASSWORD)
                .username(username)
                .password(password)
                //TODO configden okunacak
                .clientSecret("gKF2vzytWCjvpPRZcNr6rmYkiBBkrbxl")
                .build();
        return loginKeycloak.tokenManager().getAccessTokenString();
    });}
    public String createUser(String username, String password) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(false);

        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setEnabled(true);
        user.setCredentials(Collections.singletonList(credential));

        try {
            var usersResource = keycloak.realm("mesajlasma").users();
            var response = usersResource.create(user);

            if (response.getStatus() == 201) {
                return "Kullanıcı başarıyla oluşturuldu";
            } else {
                String responseBody = response.readEntity(String.class);
                System.err.println("Kullanıcı oluşturulamadı: " + response.getStatus() + " - " + responseBody);
                return "Kullanıcı oluşturulamadı: " + response.getStatus() + " - " + responseBody;
            }
        } catch (Exception e) {
            System.err.println("Kullanıcı oluşturulurken bir hata oluştu: " + e.getMessage());
            return "Kullanıcı oluşturulurken bir hata oluştu: " + e.getMessage();
        }
    }}
