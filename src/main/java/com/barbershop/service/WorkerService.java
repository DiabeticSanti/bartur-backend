package com.barbershop.service;

import com.barbershop.dto.request.WorkerRequestDTO;
import com.barbershop.dto.response.WorkerResponseDTO;
import com.barbershop.entity.Worker;
import com.barbershop.exception.ApiException;
import com.barbershop.repository.WorkerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    private final WorkerRepository workerRepository;

    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public WorkerResponseDTO createWorker(WorkerRequestDTO dto) {
        Worker worker = new Worker();
        worker.setName(dto.getName());
        worker.setEmail(dto.getEmail());
        worker.setPhone(dto.getPhone());
        worker.setSpecialty(dto.getSpecialty());
        worker.setExperience(dto.getExperience());
        worker.setRating(dto.getRating());
        worker.setAvatar(dto.getAvatar());
        worker.setAvailable(dto.isAvailable());

        Worker saved = workerRepository.save(worker);

        return mapToDTO(saved);
    }

    public WorkerResponseDTO getWorkerById(Long id) {
        Worker worker = workerRepository.findById(id)
                .orElseThrow(() -> new ApiException("Worker not found", HttpStatus.NOT_FOUND));

        return mapToDTO(worker);
    }

    public List<WorkerResponseDTO> getAllWorkers() {
        List<Worker> workers = workerRepository.findAll();
        List<WorkerResponseDTO> responseList = new ArrayList<>();

        for (Worker worker : workers) {
            responseList.add(mapToDTO(worker));
        }

        return responseList;
    }

    public WorkerResponseDTO updateWorker(Long id, WorkerRequestDTO dto) {
        Worker existing = workerRepository.findById(id)
                .orElseThrow(() -> new ApiException("Worker not found", HttpStatus.NOT_FOUND));

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        existing.setPhone(dto.getPhone());
        existing.setSpecialty(dto.getSpecialty());
        existing.setExperience(dto.getExperience());
        existing.setRating(dto.getRating());
        existing.setAvatar(dto.getAvatar());
        existing.setAvailable(dto.isAvailable());

        Worker updated = workerRepository.save(existing);
        return mapToDTO(updated);
    }

    public void deleteWorker(Long id) {
        if (!workerRepository.existsById(id)) {
            throw new ApiException("Worker not found", HttpStatus.NOT_FOUND);
        }
        workerRepository.deleteById(id);
    }

    private WorkerResponseDTO mapToDTO(Worker worker) {
        return new WorkerResponseDTO(
                worker.getId(),
                worker.getName(),
                worker.getEmail(),
                worker.getPhone(),
                worker.getSpecialty(),
                worker.getExperience(),
                worker.getRating(),
                worker.getAvatar(),
                worker.isAvailable()
        );
    }
}
