package com.lenora.staj.mesajlasma.persistence.service;

import com.lenora.staj.mesajlasma.persistence.model.Topics;
import com.lenora.staj.mesajlasma.persistence.repository.TopicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TopicsService {
    private final TopicsRepository topicsRepository;

    @Autowired
    public TopicsService(TopicsRepository topicsRepository) {
        this.topicsRepository = topicsRepository;
    }

    public Topics save(Topics topic) {
        return topicsRepository.save(topic);
    }

    public Optional<Topics> findById(UUID id) {
        return topicsRepository.findById(id);
    }
}
