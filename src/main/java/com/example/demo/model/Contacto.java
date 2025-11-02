package com.example.demo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                   // Genera getters, setters, equals, hashCode, toString
@Builder                // Permite construir objetos con Contacto.builder()...
@AllArgsConstructor     // Constructor con todos los campos
@NoArgsConstructor      // Constructor vacío (importante para frameworks)
public class Contacto {

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