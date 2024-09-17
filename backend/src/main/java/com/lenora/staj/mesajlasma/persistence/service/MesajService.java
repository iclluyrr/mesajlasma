package com.lenora.staj.mesajlasma.persistence.service;

import com.lenora.staj.mesajlasma.persistence.model.Mesaj;
import com.lenora.staj.mesajlasma.persistence.repository.MesajRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MesajService {
    private final MesajRepository mesajRepository;

    @Autowired
    public MesajService(MesajRepository mesajRepository) {
        this.mesajRepository = mesajRepository;
    }

    // Mesajı kaydet
    public Mesaj save(Mesaj mesaj) {
        return mesajRepository.save(mesaj);
    }

    // ID'ye göre mesaj bul
    public Optional<Mesaj> findById(UUID id) {
        return mesajRepository.findById(id);
    }

    // Tüm mesajları getir
    public List<Mesaj> findAll() {
        return mesajRepository.findAll();
    }

    // Belirli bir topicId'ye ait mesajları getir
    public List<Mesaj> findByTopicId(UUID topicId) {
        return mesajRepository.findByTopicId(topicId);
    }

    // Mesajı sil
    public void deleteById(UUID id) {
        if (mesajRepository.existsById(id)) {
            mesajRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Mesaj bulunamadı: " + id);
        }
    }


}
