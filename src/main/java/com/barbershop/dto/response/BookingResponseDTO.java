package com.barbershop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponseDTO {
    private Long id;
    private LocalDate date;
    private String reservationHour;
    private String atencion;
    private Long clientId;
    private Long workerId;
}
