package com.lenora.staj.mesajlasma.rest.controller;

import com.lenora.staj.mesajlasma.persistence.service.KeycloakClient;
import com.lenora.staj.mesajlasma.rest.view.LoginView;
import com.lenora.staj.mesajlasma.rest.view.RegisterView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/login")
    public Mono<ResponseEntity<String>> login(@RequestBody LoginView loginView) {
        return keycloakClient.login(loginView.getKullaniciAdi(), loginView.getSifre())
                .map(token -> ResponseEntity.ok(token))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Geçersiz kullanıcı adı veya şifre")));
    }

    @PostMapping("/createUser")
    public String createUser(@RequestBody RegisterView registerView) {
        return keycloakClient.createUser(registerView.getUsername(), registerView.getPassword());
    }

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }
}

