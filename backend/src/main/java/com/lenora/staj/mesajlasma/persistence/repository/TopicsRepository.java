package com.lenora.staj.mesajlasma.persistence.repository;

import com.lenora.staj.mesajlasma.persistence.model.Topics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TopicsRepository extends JpaRepository<Topics, UUID> {

}
