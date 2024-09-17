package com.lenora.staj.mesajlasma.rest.model;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateMessageRequest {
    private String baslik;
    private String icerik;
    private UUID topicId;
}
