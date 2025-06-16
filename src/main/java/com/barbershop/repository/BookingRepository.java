package com.barbershop.repository;

import com.barbershop.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    boolean existsByWorkerIdAndDateAndReservationHour(Long workerId, LocalDate date, String reservationHour);
    List<Booking> findByClientId(Long clientId);
    List<Booking> findByWorkerIdAndDate(Long workerId, LocalDate date);
}
