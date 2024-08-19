package com.lenora.staj.mesajlasma.persistence.repository;

import com.lenora.staj.mesajlasma.persistence.model.Mesaj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MesajRepository extends JpaRepository<Mesaj, UUID> {
}
