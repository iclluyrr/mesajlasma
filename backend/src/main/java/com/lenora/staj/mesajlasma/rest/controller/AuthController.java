package com.lenora.staj.mesajlasma.rest.controller;

import com.lenora.staj.mesajlasma.persistence.service.KeycloakClient;
import com.lenora.staj.mesajlasma.rest.view.LoginView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final KeycloakClient keycloakClient;

    @Autowired
    public AuthController(KeycloakClient keycloakClient) {
        this.keycloakClient = keycloakClient;
    }

    @PostMapping("/giris")
    public Mono<String>  girisYap(@RequestBody LoginView loginView) {
        Mono<String> login = keycloakClient.login(loginView.getKullaniciAdi(), loginView.getSifre());
        return login;

    }

}
