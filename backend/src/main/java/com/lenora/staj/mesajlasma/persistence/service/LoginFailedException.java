package com.lenora.staj.mesajlasma.persistence.service;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Login failed")
public class LoginFailedException extends RuntimeException {
    public LoginFailedException(){
        super("Geçersiz kimlik bilgileri veya diğer nedenlerden dolayı oturum açma başarısız oldu.");
    }
}
