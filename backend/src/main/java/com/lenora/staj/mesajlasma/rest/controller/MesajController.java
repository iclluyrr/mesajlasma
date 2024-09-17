package com.lenora.staj.mesajlasma.rest.controller;

import com.lenora.staj.mesajlasma.persistence.model.Mesaj;
import com.lenora.staj.mesajlasma.persistence.model.Topics;
import com.lenora.staj.mesajlasma.persistence.service.MesajService;
import com.lenora.staj.mesajlasma.persistence.service.TopicsService;
import com.lenora.staj.mesajlasma.rest.model.CreateMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/messages")
public class MesajController {

    private final MesajService mesajService;
    private final TopicsService topicsService;

    @Autowired
    public MesajController(MesajService mesajService, TopicsService topicsService) {
        this.mesajService = mesajService;
        this.topicsService = topicsService;
    }

    @PostMapping
    public ResponseEntity<Mesaj> createMessage(@RequestBody CreateMessageRequest createMessageRequest) {
        Mesaj mesaj = new Mesaj();
        mesaj.setBaslik(createMessageRequest.getBaslik());
        mesaj.setIcerik(createMessageRequest.getIcerik());

        // Kullanıcı adı (sender) almak için Spring Security kullan
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        mesaj.setSender(currentUserName);

        // Topic ile ilişkilendirme
        if (createMessageRequest.getTopicId() != null) {
            Topics topic = topicsService.findById(createMessageRequest.getTopicId())
                    .orElseThrow(() -> new IllegalArgumentException("Geçersiz Topic ID"));
            mesaj.setTopic(topic);
        }

        // Mesaj nesnesini kaydet
        Mesaj savedMesaj = mesajService.save(mesaj);
        return ResponseEntity.ok(savedMesaj);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesaj> getMessage(@PathVariable UUID id) {
        return mesajService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/topic/{topicId}")
    public ResponseEntity<List<Mesaj>> getMessagesByTopic(@PathVariable String topicId) {
        try {
            UUID uuid = UUID.fromString(topicId);
            List<Mesaj> mesajlar = mesajService.findByTopicId(uuid);
            if (mesajlar.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(mesajlar);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Geçersiz UUID formatı
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Genel hata
        }
    }
}
