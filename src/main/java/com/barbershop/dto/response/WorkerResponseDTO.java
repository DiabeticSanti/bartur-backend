package com.barbershop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkerResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String specialty;
    private String experience;
    private double rating;
    private String avatar;
    private boolean available;
}
