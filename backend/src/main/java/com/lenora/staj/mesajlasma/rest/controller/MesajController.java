package com.lenora.staj.mesajlasma.rest.controller;

import com.lenora.staj.mesajlasma.persistence.model.Mesaj;
import com.lenora.staj.mesajlasma.persistence.service.MesajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/messages")
public class MesajController {
    private final MesajService mesajService;

    @Autowired
    public MesajController(MesajService mesajService) {
        this.mesajService = mesajService;
    }

    @PostMapping
    public ResponseEntity<Mesaj> createMessage(@RequestBody Mesaj mesaj) {
        Mesaj savedMesaj = mesajService.save(mesaj);
        return ResponseEntity.ok(savedMesaj);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesaj> getMessage(@PathVariable UUID id) {
        return mesajService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
