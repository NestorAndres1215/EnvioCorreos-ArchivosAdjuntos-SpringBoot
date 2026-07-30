package com.example.demo.service;

import com.example.demo.dto.ContactoRequest;
import com.example.demo.model.Contacto;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface ContactoService {

    String enviar(
            ContactoRequest request,
            MultipartFile archivo
    ) throws Exception;

}
