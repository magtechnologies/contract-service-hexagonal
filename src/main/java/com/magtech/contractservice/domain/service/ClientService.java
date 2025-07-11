package com.magtech.contractservice.domain.service;

import com.magtech.contractservice.domain.model.Client;
import com.magtech.contractservice.domain.port.in.ClientUseCase;
import com.magtech.contractservice.domain.port.out.ClientRepository;
import com.magtech.contractservice.shared.exception.ClientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService implements ClientUseCase {

    private final ClientRepository clientRepository;

    @Override
    public Client createClient(Client client) {
        client.setCreatedAt(LocalDate.now());
        client.setUpdatedAt(LocalDate.now());

        if (client.getActive() == null) {
            client.setActive(true);
        }

        return clientRepository.save(client);
    }

    @Override
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public List<Client> findByActive(Boolean active) {
        return clientRepository.findByActive(active);
    }

    @Override
    public Optional<Client> findByDocument(String document) {
        return clientRepository.findByDocument(document);
    }

    @Override
    public Client updateClient(Long id, Client client) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found with id: " + id));

        client.setId(id);
        client.setCreatedAt(existingClient.getCreatedAt());
        client.setUpdatedAt(LocalDate.now());

        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException("Client not found with id: " + id);
        }
        clientRepository.deleteById(id);
    }
}