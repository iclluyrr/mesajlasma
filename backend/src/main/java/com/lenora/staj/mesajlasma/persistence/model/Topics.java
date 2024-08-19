package com.lenora.staj.mesajlasma.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
public class Topics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String baslik;
    private String aciklama;

    @OneToMany(mappedBy = "topic", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Mesaj> mesajlar;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Topics other = (Topics) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
