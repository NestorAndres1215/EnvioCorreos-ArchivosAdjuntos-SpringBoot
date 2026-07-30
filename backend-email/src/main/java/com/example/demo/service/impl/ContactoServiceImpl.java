package com.example.demo.service.impl;

import com.example.demo.dto.ContactoRequest;
import com.example.demo.mapper.ContactoMapper;
import com.example.demo.model.Contacto;
import com.example.demo.service.ArchivoService;
import com.example.demo.service.ContactoService;
import com.example.demo.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
@RequiredArgsConstructor
public class ContactoServiceImpl implements ContactoService {


    private final EmailService emailService;

    private final ArchivoService archivoService;

    private final ContactoMapper mapper;



    @Override
    public String enviar(ContactoRequest request, MultipartFile archivo) throws Exception {

        Contacto contacto = mapper.toModel(request);
        File archivoTemporal = archivoService.crearArchivo(archivo);
        emailService.enviarEmail(contacto, archivoTemporal);
        archivoService.eliminarArchivo(archivoTemporal);
        return "Correo enviado correctamente";

    }

}