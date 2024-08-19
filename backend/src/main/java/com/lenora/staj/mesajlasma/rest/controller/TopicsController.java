package com.lenora.staj.mesajlasma.rest.controller;

import com.lenora.staj.mesajlasma.persistence.model.Topics;
import com.lenora.staj.mesajlasma.persistence.service.TopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/topics")
public class TopicsController {
    private final TopicsService topicsService;

    @Autowired
    public TopicsController(TopicsService topicsService) {
        this.topicsService = topicsService;
    }

    @PostMapping
    public ResponseEntity<Topics> createTopic(@RequestBody Topics topic) {
        Topics savedTopic = topicsService.save(topic);
        return ResponseEntity.ok(savedTopic);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topics> getTopic(@PathVariable UUID id) {
        return topicsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
