package com.barbershop.controller;

import com.barbershop.dto.request.WorkerRequestDTO;
import com.barbershop.dto.response.CustomResponseDTO;
import com.barbershop.dto.response.WorkerResponseDTO;
import com.barbershop.service.WorkerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // Permite acceso desde cualquier origen
@RestController
@RequestMapping("/api/workers")
public class WorkerController {

    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createWorker(@Valid @RequestBody WorkerRequestDTO dto) {
        WorkerResponseDTO worker = workerService.createWorker(dto);
        CustomResponseDTO res = new CustomResponseDTO("Worker created", HttpStatus.OK);
        res.setResponseObject(worker);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getWorkerById(@PathVariable Long id) {
        WorkerResponseDTO worker = workerService.getWorkerById(id);
        CustomResponseDTO res = new CustomResponseDTO("Worker retrieved", HttpStatus.OK);
        res.setResponseObject(worker);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllWorkers() {
        List<WorkerResponseDTO> workers = workerService.getAllWorkers();
        CustomResponseDTO res = new CustomResponseDTO("Workers retrieved", HttpStatus.OK);
        res.setResponseObject(workers);
        return ResponseEntity.ok(res);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Object> updateWorker(@PathVariable Long id, @RequestBody WorkerRequestDTO dto) {
        WorkerResponseDTO updated = workerService.updateWorker(id, dto);
        CustomResponseDTO res = new CustomResponseDTO("Worker updated", HttpStatus.OK);
        res.setResponseObject(updated);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteWorker(@PathVariable Long id) {
        workerService.deleteWorker(id);
        CustomResponseDTO res = new CustomResponseDTO("Worker deleted", HttpStatus.OK);
        res.setResponseObject(null);
        return ResponseEntity.ok(res);
    }
}
