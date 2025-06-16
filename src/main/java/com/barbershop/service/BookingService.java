package com.barbershop.service;

import com.barbershop.dto.request.BookingRequestDTO;
import com.barbershop.dto.response.BookingResponseDTO;
import com.barbershop.dto.response.WorkerResponseDTO;
import com.barbershop.entity.Booking;
import com.barbershop.entity.Client;
import com.barbershop.entity.Worker;
import com.barbershop.exception.ApiException;
import com.barbershop.repository.BookingRepository;
import com.barbershop.repository.ClientRepository;
import com.barbershop.repository.WorkerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ClientRepository clientRepository;
    private final WorkerRepository workerRepository;

    public BookingService(BookingRepository bookingRepository, ClientRepository clientRepository, WorkerRepository workerRepository) {
        this.bookingRepository = bookingRepository;
        this.clientRepository = clientRepository;
        this.workerRepository = workerRepository;
    }

    public BookingResponseDTO createBooking(BookingRequestDTO requestDTO) {
        Optional<Client> client = clientRepository.findById(requestDTO.getClientId());
        Optional<Worker> worker = workerRepository.findById(requestDTO.getWorkerId());

        validateBooking(client, worker, requestDTO.getDate(), requestDTO.getReservationHour());

        Booking booking = new Booking();
        booking.setClient(client.get());
        booking.setWorker(worker.get());
        booking.setAtencion(requestDTO.getAtencion());
        booking.setDate(requestDTO.getDate());
        booking.setReservationHour(requestDTO.getReservationHour());
        booking.setState(true);

        Booking savedBooking = bookingRepository.save(booking);

        return new BookingResponseDTO(
                savedBooking.getId(),
                savedBooking.getDate(),
                savedBooking.getReservationHour(),
                savedBooking.getAtencion(),
                savedBooking.getClient().getId(),
                savedBooking.getWorker().getId()
        );
    }
    public List<BookingResponseDTO> getAllBooking() {
        List<Booking> bookings = bookingRepository.findAll();
        List<BookingResponseDTO> responseList = new ArrayList<>();

        for (Booking booking : bookings) {
            responseList.add(new BookingResponseDTO(
                    booking.getId(),
                    booking.getDate(),
                    booking.getReservationHour(),
                    booking.getAtencion(),
                    booking.getClient().getId(),
                    booking.getWorker().getId()
            ));
        }

        return responseList;
    }


    private void validateBooking(Optional<Client> client, Optional<Worker> worker, LocalDate date, String hour) {
        if (client.isEmpty()) throw new ApiException("Client not found", HttpStatus.NOT_FOUND);
        if (worker.isEmpty()) throw new ApiException("Worker not found", HttpStatus.NOT_FOUND);
        if (!worker.get().isAvailable()) throw new ApiException("Worker is not available", HttpStatus.CONFLICT);
        boolean conflict = bookingRepository.existsByWorkerIdAndDateAndReservationHour(worker.get().getId(), date, hour);
        if (conflict) throw new ApiException("El trabajador ya tiene una reserva en esa fecha y hora", HttpStatus.CONFLICT);
    }


    public List<BookingResponseDTO> getBookingsByClientId(Long clientId) {
        if (!clientRepository.existsById(clientId)) {
            throw new ApiException("Client not found", HttpStatus.NOT_FOUND);
        }

        List<Booking> bookings = bookingRepository.findByClientId(clientId);
        List<BookingResponseDTO> responses = new ArrayList<>();

        for (Booking booking : bookings) {
            responses.add(new BookingResponseDTO(
                    booking.getId(),
                    booking.getDate(),
                    booking.getReservationHour(),
                    booking.getAtencion(),
                    booking.getClient().getId(),
                    booking.getWorker().getId()
            ));
        }

        return responses;
    }

    public List<BookingResponseDTO> getBookingsByWorkerAndDate(Long workerId, LocalDate date) {
        if (!workerRepository.existsById(workerId)) {
            throw new ApiException("Worker not found", HttpStatus.NOT_FOUND);
        }

        List<Booking> bookings = bookingRepository.findByWorkerIdAndDate(workerId, date);
        List<BookingResponseDTO> responses = new ArrayList<>();

        for (Booking booking : bookings) {
            responses.add(new BookingResponseDTO(
                    booking.getId(),
                    booking.getDate(),
                    booking.getReservationHour(),
                    booking.getAtencion(),
                    booking.getClient().getId(),
                    booking.getWorker().getId()
            ));
        }

        return responses;
    }

    public BookingResponseDTO cancelBooking(Long bookingId) {
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);

        if (!optionalBooking.isPresent()) {
            throw new ApiException("Booking not found", HttpStatus.NOT_FOUND);
        }

        Booking booking = optionalBooking.get();

        if (!booking.isState()) {
            throw new ApiException("Booking is already cancelled", HttpStatus.CONFLICT);
        }

        booking.setState(false);
        Booking updated = bookingRepository.save(booking);

        return new BookingResponseDTO(
                updated.getId(),
                updated.getDate(),
                updated.getReservationHour(),
                updated.getAtencion(),
                updated.getClient().getId(),
                updated.getWorker().getId()
        );
    }


}
