package com.example.demo.service;

import com.example.demo.model.Contacto;
import jakarta.mail.MessagingException;

import java.io.File;

public interface EmailService {
    void enviarEmail(Contacto contacto, File archivo) throws MessagingException;
}