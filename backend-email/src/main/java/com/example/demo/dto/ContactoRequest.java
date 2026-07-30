package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactoRequest {

    @NotBlank(message = "El nombre es obligatorio.")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres.")
    private String nombre;


    @NotBlank(message = "El correo es obligatorio.")
    @Email(message = "Debe proporcionar un correo válido.")
    private String correo;


    @NotBlank(message = "El mensaje no puede estar vacío.")
    @Size(max = 500, message = "El mensaje no puede superar los 500 caracteres.")
    private String mensaje;


    @NotBlank(message = "El asunto es obligatorio.")
    @Size(max = 150, message = "El asunto no puede superar los 150 caracteres.")
    private String asunto;
}