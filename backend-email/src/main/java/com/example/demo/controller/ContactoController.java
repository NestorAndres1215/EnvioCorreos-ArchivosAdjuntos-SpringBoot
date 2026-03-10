package com.example.demo.controller;

import com.example.demo.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Contacto;
import com.example.demo.service.EmailServiceImpl;

import java.io.File;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ContactoController {


    private final EmailService emailService;

    @PostMapping("/contacto/enviar")
    public String enviarCorreo(@ModelAttribute Contacto contacto, @RequestParam(value = "archivo", required = false) MultipartFile archivo) throws IOException, MessagingException {

        File archivoFisico = null;

        if (archivo != null && !archivo.isEmpty()) {
            archivoFisico = File.createTempFile("upload-", archivo.getOriginalFilename());
            archivo.transferTo(archivoFisico);
        }

        emailService.enviarEmail(contacto, archivoFisico);

        return "Correo enviado correctamente";
    }
}
