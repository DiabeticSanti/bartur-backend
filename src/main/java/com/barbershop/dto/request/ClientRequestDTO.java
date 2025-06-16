package com.barbershop.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    private String name;

    @Size(min = 7, max = 15, message = "El número de teléfono debe tener entre 7 y 15 caracteres")
    private String phone;

    @Size(max = 100, message = "La contraseña no puede tener más de 100 caracteres")
    private String password;


    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "Debe ingresar un correo válido")
    @Size(max = 100, message = "El correo no puede tener más de 100 caracteres")
    private String email;


}
