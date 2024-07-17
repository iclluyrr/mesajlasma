package com.lenora.staj.mesajlasma.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Entity
@Data
public class Mesaj {
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Mesaj other = (Mesaj) obj;
        return Objects.equals(id, other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private String metin ;
}
