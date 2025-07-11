package com.magtech.contractservice.domain.port.in;

import com.magtech.contractservice.domain.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientUseCase {
    Client createClient(Client client);
    Optional<Client> findById(Long id);
    List<Client> findAll();
    List<Client> findByActive(Boolean active);
    Optional<Client> findByDocument(String document);
    Client updateClient(Long id, Client client);
    void deleteClient(Long id);
}