package com.lenora.staj.mesajlasma.persistence.service;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Register failed")
public class RegistrationFailedException extends RuntimeException {
    public RegistrationFailedException(){
        super("Geçersiz kimlik bilgileri veya diğer nedenlerden dolayı kayıt olma başarısız oldu.");
    }
}
