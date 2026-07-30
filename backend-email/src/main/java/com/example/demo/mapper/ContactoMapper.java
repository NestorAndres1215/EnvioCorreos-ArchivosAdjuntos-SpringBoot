package com.example.demo.mapper;


import com.example.demo.dto.ContactoRequest;
import com.example.demo.model.Contacto;
import org.springframework.stereotype.Component;

@Component
public class ContactoMapper {


    public Contacto toModel(ContactoRequest request){

        return Contacto.builder()
                .nombre(request.getNombre())
                .correo(request.getCorreo())
                .asunto(request.getAsunto())
                .mensaje(request.getMensaje())
                .build();

    }

}