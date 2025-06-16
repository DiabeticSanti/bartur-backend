package com.barbershop.controller;

import com.barbershop.dto.request.BookingRequestDTO;
import com.barbershop.dto.response.BookingResponseDTO;
import com.barbershop.dto.response.CustomResponseDTO;
import com.barbershop.dto.response.WorkerResponseDTO;
import com.barbershop.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*") // Permite acceso desde cualquier origen
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createBooking(@Valid @RequestBody BookingRequestDTO requestDTO) {
        BookingResponseDTO responseDTO = bookingService.createBooking(requestDTO);
        CustomResponseDTO res = new CustomResponseDTO("Booking created", HttpStatus.OK);
        res.setResponseObject(responseDTO);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/client/{clientId}/all")
    public ResponseEntity<Object> getBookingsByClient(@PathVariable Long clientId) {
        List<BookingResponseDTO> bookings = bookingService.getBookingsByClientId(clientId);
        CustomResponseDTO res = new CustomResponseDTO("Client bookings retrieved", HttpStatus.OK);
        res.setResponseObject(bookings);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/worker/{workerId}/date")
    public ResponseEntity<Object> getWorkerBookingsByDate(
            @PathVariable Long workerId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<BookingResponseDTO> bookings = bookingService.getBookingsByWorkerAndDate(workerId, date);
        CustomResponseDTO res = new CustomResponseDTO("Worker bookings for date retrieved", HttpStatus.OK);
        res.setResponseObject(bookings);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllWorkers() {
        List<BookingResponseDTO> booking = bookingService.getAllBooking();
        CustomResponseDTO res = new CustomResponseDTO("Booking retreived", HttpStatus.OK);
        res.setResponseObject(booking);
        return ResponseEntity.ok(res);
    }


    @PutMapping("/{bookingId}/cancel")
    public ResponseEntity<Object> cancelBooking(@PathVariable Long bookingId) {
        BookingResponseDTO response = bookingService.cancelBooking(bookingId);
        CustomResponseDTO res = new CustomResponseDTO("Booking cancelled successfully", HttpStatus.OK);
        res.setResponseObject(response);
        return ResponseEntity.ok(res);
    }




}
