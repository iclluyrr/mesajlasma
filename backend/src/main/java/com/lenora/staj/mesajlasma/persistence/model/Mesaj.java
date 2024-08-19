package com.lenora.staj.mesajlasma.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Entity
@Data
public class Mesaj {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String metin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id") // Mesajın hangi konuya ait olduğunu belirtir
    private Topics topic;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Mesaj other = (Mesaj) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
