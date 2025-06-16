package com.barbershop.controller;

import com.barbershop.dto.request.BarberServiceRequestDTO;
import com.barbershop.dto.response.CustomResponseDTO;
import com.barbershop.dto.response.BarberServiceResponseDTO;
import com.barbershop.service.BarberServiceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // Permite acceso desde cualquier origen
@RestController
@RequestMapping("/api/services")
public class BarberServiceController {

    private final BarberServiceService barberServiceService;

    public BarberServiceController(BarberServiceService barberServiceService) {
        this.barberServiceService = barberServiceService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createService(@Valid @RequestBody BarberServiceRequestDTO barberServiceRequestDTO) {
        BarberServiceResponseDTO service = barberServiceService.createService(barberServiceRequestDTO);
        CustomResponseDTO res = new CustomResponseDTO("Service created", HttpStatus.OK);
        res.setResponseObject(service);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllServices() {
        List<BarberServiceResponseDTO> services = barberServiceService.getAllServices();
        CustomResponseDTO res = new CustomResponseDTO("Services retrieved successfully", HttpStatus.OK);
        res.setResponseObject(services);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateService(@PathVariable Long id, @RequestBody BarberServiceRequestDTO dto) {
        BarberServiceResponseDTO updatedService = barberServiceService.updateService(id, dto);
        CustomResponseDTO res = new CustomResponseDTO("Service updated successfully", HttpStatus.OK);
        res.setResponseObject(updatedService);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteService(@PathVariable Long id) {
        barberServiceService.deleteService(id);
        CustomResponseDTO res = new CustomResponseDTO("Service deleted successfully", HttpStatus.OK);
        res.setResponseObject(null);
        return ResponseEntity.ok(res);
    }
}
