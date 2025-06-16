package com.barbershop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDTO {

    private Long clientId;
    private Long workerId;
    private String atencion;
    private LocalDate date;
    private String  reservationHour;
}
