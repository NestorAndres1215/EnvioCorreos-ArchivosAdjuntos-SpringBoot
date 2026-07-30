package com.example.demo.service.impl;

import com.example.demo.model.Contacto;
import com.example.demo.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import java.io.File;



@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    private static final String FROM = "no-reply@midominio.com";


    private static final String HTML = """
            <html>
            <body>

            <h2>Nuevo contacto</h2>

            <p><b>Nombre:</b> %s</p>
            <p><b>Correo:</b> %s</p>
            <p><b>Asunto:</b> %s</p>
            <p><b>Mensaje:</b> %s</p>

            </body>
            </html>
            """;

    @Override
    public void enviarEmail(Contacto contacto, File archivo) throws MessagingException {

        MimeMessage mensaje = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, "UTF-8");

        helper.setFrom(FROM);

        helper.setTo(contacto.getCorreo());

        helper.setSubject(contacto.getAsunto());

        helper.setText(String.format(
                HTML, contacto.getNombre(), contacto.getCorreo(), contacto.getAsunto(), contacto.getMensaje()),
                true
        );

        if(archivo != null && archivo.exists()){
            helper.addAttachment(archivo.getName(), new FileSystemResource(archivo));
        }

        mailSender.send(mensaje);

    }

}