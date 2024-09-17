package com.lenora.staj.mesajlasma.persistence.repository;

import com.lenora.staj.mesajlasma.persistence.model.Mesaj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MesajRepository extends JpaRepository<Mesaj, UUID> {

    // Belirli bir topicId'ye ait tüm mesajları getirir
    List<Mesaj> findByTopicId(UUID topicId);
}
