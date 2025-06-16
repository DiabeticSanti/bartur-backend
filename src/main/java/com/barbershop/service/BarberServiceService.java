package com.barbershop.service;

import com.barbershop.dto.request.BarberServiceRequestDTO;
import com.barbershop.dto.response.BarberServiceResponseDTO;
import com.barbershop.entity.BarberService;
import com.barbershop.exception.ApiException;
import com.barbershop.repository.BarberServiceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BarberServiceService {

    private final BarberServiceRepository barberServiceRepository;

    public BarberServiceService(BarberServiceRepository barberServiceRepository) {
        this.barberServiceRepository = barberServiceRepository;
    }

    public BarberServiceResponseDTO createService(BarberServiceRequestDTO barberServiceRequestDTO) {
        if (barberServiceRepository.existsByName(barberServiceRequestDTO.getName())) {
            throw new ApiException("El servicio ya existe", HttpStatus.CONFLICT);
        }

        BarberService barberService = new BarberService();
        barberService.setName(barberServiceRequestDTO.getName());
        barberService.setDescription(barberServiceRequestDTO.getDescription());
        barberService.setPrice(barberServiceRequestDTO.getPrice());

        BarberService savedBarberService = barberServiceRepository.save(barberService);

        BarberServiceResponseDTO barberServiceResponseDTO = new BarberServiceResponseDTO();
        barberServiceResponseDTO.setId(savedBarberService.getId());
        barberServiceResponseDTO.setName(savedBarberService.getName());
        barberServiceResponseDTO.setDescription(savedBarberService.getDescription());
        barberServiceResponseDTO.setPrice(savedBarberService.getPrice());

        return barberServiceResponseDTO;
    }

    public List<BarberServiceResponseDTO> getAllServices() {
        List<BarberService> barberServices = barberServiceRepository.findAll();

        List<BarberServiceResponseDTO> responseList = new ArrayList<>();

        for (BarberService barberService : barberServices) {
            BarberServiceResponseDTO dto = new BarberServiceResponseDTO();
            dto.setId(barberService.getId());
            dto.setName(barberService.getName());
            dto.setDescription(barberService.getDescription());
            dto.setPrice(barberService.getPrice());
            responseList.add(dto);
        }

        return responseList;
    }

    public BarberServiceResponseDTO updateService(Long id, BarberServiceRequestDTO dto) {
        Optional<BarberService> optionalService = barberServiceRepository.findById(id);

        if (!optionalService.isPresent()) {
            throw new ApiException("Service not found", HttpStatus.NOT_FOUND);
        }

        BarberService existingService = optionalService.get();

        if (!existingService.getName().equals(dto.getName()) &&
                barberServiceRepository.existsByName(dto.getName())) {
            throw new ApiException("Ya existe otro servicio con ese nombre", HttpStatus.CONFLICT);
        }

        existingService.setName(dto.getName());
        existingService.setDescription(dto.getDescription());
        existingService.setPrice(dto.getPrice());

        BarberService updated = barberServiceRepository.save(existingService);

        BarberServiceResponseDTO response = new BarberServiceResponseDTO();
        response.setId(updated.getId());
        response.setName(updated.getName());
        response.setDescription(updated.getDescription());
        response.setPrice(updated.getPrice());

        return response;
    }

    public void deleteService(Long id) {
        if (!barberServiceRepository.existsById(id)) {
            throw new ApiException("Service not found", HttpStatus.NOT_FOUND);
        }

        barberServiceRepository.deleteById(id);
    }

}
