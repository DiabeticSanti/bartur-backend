package com.barbershop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BarberServiceResponseDTO {

    private Long id;
    private String name;
    private String description;
    private double price;
}
