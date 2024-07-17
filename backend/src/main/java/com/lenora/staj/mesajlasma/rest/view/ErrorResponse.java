package com.lenora.staj.mesajlasma.rest.view;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import lombok.Data;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Data
public class ErrorResponse {
    private String timestamp;
    private int status;
    private String error;
    private String trace;
    private String message;
    private String path;

}

