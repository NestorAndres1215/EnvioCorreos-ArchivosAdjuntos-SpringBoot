package com.example.demo.service;

package com.example.demo.service;

import com.example.demo.model.Contacto;
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
public class EmailService {

    private final JavaMailSender mailSender;

    // ===================== CONSTANTES =====================
    private static final String CHARSET = "UTF-8";
    private static final String MAIL_NO_REPLY = "no-reply@midominio.com"; // Cambia este correo
    private static final String EMAIL_AUTO_MSG = "Este es un correo automático, por favor no respondas.";

    private static final String HTML_TEMPLATE = """
            <html>
            <head>
                <style>
                    body { font-family: Arial, sans-serif; background-color: #f4f4f9; padding: 20px; color: #333333; }
                    h3 { color: #4CAF50; }
                    .content { background-color: #ffffff; border-radius: 8px; padding: 20px; 
                               box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }
                    p { font-size: 16px; line-height: 1.6; margin: 10px 0; }
                    .info { font-weight: bold; color: #333333; }
                    .footer { font-size: 14px; color: #777777; margin-top: 20px; text-align: center; }
                </style>
            </head>
            <body>
                <div class="content">
                    <h3>¡Tienes un nuevo mensaje!</h3>
                    <p><span class="info">Nombre:</span> %s</p>
                    <p><span class="info">Correo:</span> %s</p>
                    <p><span class="info">Asunto:</span> %s</p>
                    <p><span class="info">Mensaje:</span> %s</p>
                </div>
                <div class="footer">
                    <p>%s</p>
                </div>
            </body>
            </html>
            """;

    // ===================== MÉTODO PRINCIPAL =====================
    public void enviarEmail(Contacto contacto, File archivo) throws MessagingException {

        MimeMessage mensaje = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true, CHARSET);

        helper.setTo(contacto.getCorreo());
        helper.setSubject(contacto.getAsunto());
        helper.setFrom(MAIL_NO_REPLY); // ✅ No usamos el mismo correo del usuario

        String htmlFinal = String.format(
                HTML_TEMPLATE,
                contacto.getNombre(),
                contacto.getCorreo(),
                contacto.getAsunto(),
                contacto.getMensaje(),
                EMAIL_AUTO_MSG
        );

        helper.setText(htmlFinal, true);

        // Adjuntar archivo si existe
        if (archivo != null && archivo.exists()) {
            helper.addAttachment(archivo.getName(), new FileSystemResource(archivo));
        }

        mailSender.send(mensaje);
    }
}
