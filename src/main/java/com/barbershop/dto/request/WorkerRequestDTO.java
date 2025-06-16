package com.barbershop.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkerRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El correo electrónico es obligatorio")
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    private String phone;

    @NotBlank(message = "La especialidad es obligatoria")
    private String specialty;

    @NotBlank(message = "La experiencia es obligatoria")
    private String experience;

    @NotNull(message = "La calificación es obligatoria")
    private Double rating;

    private boolean available;

    private String avatar;
}
