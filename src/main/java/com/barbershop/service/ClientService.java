package com.barbershop.service;

import com.barbershop.dto.request.ClientRequestDTO;
import com.barbershop.dto.response.ClientResponseDTO;
import com.barbershop.entity.Client;
import com.barbershop.exception.ApiException;
import com.barbershop.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientResponseDTO createClient(ClientRequestDTO dto) {
        if (clientRepository.existsByEmail(dto.getEmail())) {
            throw new ApiException("El correo ya está registrado", HttpStatus.CONFLICT);
        }

        Client client = new Client();
        client.setName(dto.getName());
        client.setPassword(dto.getPassword());
        client.setPhone(dto.getPhone());
        client.setEmail(dto.getEmail());

        Client saved = clientRepository.save(client);

        ClientResponseDTO response = new ClientResponseDTO();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setPassword(dto.getPassword());
        response.setPhone(saved.getPhone());
        response.setEmail(saved.getEmail());

        return response;
    }


    public ClientResponseDTO getClientById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (!optionalClient.isPresent()) throw new ApiException("Client not found", HttpStatus.NOT_FOUND);
        Client client = optionalClient.get();

        ClientResponseDTO response = new ClientResponseDTO();
        response.setId(client.getId());
        response.setName(client.getName());
        response.setPassword(client.getPassword());
        response.setPhone(client.getPhone());
        response.setEmail(client.getEmail());

        return response;
    }

    public List<ClientResponseDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();

        List<ClientResponseDTO> responseList = new ArrayList<>();

        for (Client client : clients) {
            ClientResponseDTO dto = new ClientResponseDTO();
            dto.setId(client.getId());
            dto.setName(client.getName());
            dto.setPassword(client.getPassword());
            dto.setPhone(client.getPhone());
            dto.setEmail(client.getEmail());
            responseList.add(dto);
        }

        return responseList;
    }

    public ClientResponseDTO updateClient(Long id, ClientRequestDTO dto) {
        Optional<Client> optionalClient = clientRepository.findById(id);

        if (!optionalClient.isPresent()) {
            throw new ApiException("Client not found", HttpStatus.NOT_FOUND);
        }

        Client existingClient = optionalClient.get();

        if (!existingClient.getEmail().equals(dto.getEmail()) &&
                clientRepository.existsByEmail(dto.getEmail())) {
            throw new ApiException("El correo ya está registrado por otro cliente", HttpStatus.CONFLICT);
        }

        existingClient.setName(dto.getName());
        existingClient.setPassword(dto.getPassword());
        existingClient.setPhone(dto.getPhone());
        existingClient.setEmail(dto.getEmail());

        Client updated = clientRepository.save(existingClient);

        ClientResponseDTO response = new ClientResponseDTO();
        response.setId(updated.getId());
        response.setName(updated.getName());
        response.setPassword(dto.getPassword());
        response.setPhone(updated.getPhone());
        response.setEmail(updated.getEmail());

        return response;
    }

    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ApiException("Client not found", HttpStatus.NOT_FOUND);
        }

        clientRepository.deleteById(id);
    }

}
