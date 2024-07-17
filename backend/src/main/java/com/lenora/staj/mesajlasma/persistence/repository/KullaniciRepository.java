package com.lenora.staj.mesajlasma.persistence.repository;

import com.lenora.staj.mesajlasma.persistence.model.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface KullaniciRepository extends JpaRepository<Kullanici, UUID> {
    Kullanici findFirstByKullaniciAdi(String kullaniciAdi);
}
