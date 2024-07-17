package com.lenora.staj.mesajlasma.persistence.service;

import com.lenora.staj.mesajlasma.persistence.model.Kullanici;
import com.lenora.staj.mesajlasma.persistence.repository.KullaniciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KullaniciService {
    @Autowired
    KullaniciRepository kullaniciRepository;

    @Transactional
    public void test() {
        kullaniciRepository.save(new Kullanici());
    }

    }

