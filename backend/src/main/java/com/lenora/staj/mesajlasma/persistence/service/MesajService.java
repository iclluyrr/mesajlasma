package com.lenora.staj.mesajlasma.persistence.service;

import com.lenora.staj.mesajlasma.persistence.model.Mesaj;
import com.lenora.staj.mesajlasma.persistence.repository.MesajRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MesajService {
    private final MesajRepository mesajRepository;

    @Autowired
    public MesajService(MesajRepository mesajRepository) {
        this.mesajRepository = mesajRepository;
    }

    public Mesaj save(Mesaj mesaj) {
        return mesajRepository.save(mesaj);
    }

    public Optional<Mesaj> findById(UUID id) {
        return mesajRepository.findById(id);
    }
}
