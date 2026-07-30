package com.example.demo.controller;

import com.example.demo.dto.ContactoRequest;
import com.example.demo.service.ContactoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ContactoController {


    private final ContactoService contactoService;

    @PostMapping(value = "/contacto/enviar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> enviarCorreo(
            @Valid @RequestPart("contacto") ContactoRequest request,
            @RequestPart(value = "archivo", required = false) MultipartFile archivo) throws Exception {

        return ResponseEntity.ok(contactoService.enviar(request, archivo));

    }

}
