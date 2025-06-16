package com.barbershop.controller;

import com.barbershop.dto.request.ClientRequestDTO;
import com.barbershop.dto.response.ClientResponseDTO;
import com.barbershop.dto.response.CustomResponseDTO;
import com.barbershop.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*") // Permite acceso desde cualquier origen
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {this.clientService = clientService;}

    @PostMapping("/create")
    public ResponseEntity<Object> createClient(@Valid @RequestBody ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO client = clientService.createClient(clientRequestDTO);
        CustomResponseDTO res = new CustomResponseDTO("Client created", HttpStatus.OK);
        res.setResponseObject(client);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getClientById(@PathVariable Long id) {
        ClientResponseDTO client = clientService.getClientById(id);
        CustomResponseDTO res = new CustomResponseDTO("Client found", HttpStatus.OK);
        res.setResponseObject(client);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllClients() {
        List<ClientResponseDTO> clients = clientService.getAllClients();
        CustomResponseDTO res = new CustomResponseDTO("Clients retrieved successfully", HttpStatus.OK);
        res.setResponseObject(clients);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable Long id, @RequestBody ClientRequestDTO dto) {
        ClientResponseDTO updatedClient = clientService.updateClient(id, dto);
        CustomResponseDTO res = new CustomResponseDTO("Client updated successfully", HttpStatus.OK);
        res.setResponseObject(updatedClient);
        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        CustomResponseDTO res = new CustomResponseDTO("Client deleted successfully", HttpStatus.OK);
        res.setResponseObject(null);
        return ResponseEntity.ok(res);
    }


}
