package com.magtech.contractservice.domain.port.out;

import com.magtech.contractservice.domain.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    Client save(Client client);
    Optional<Client> findById(Long id);
    List<Client> findAll();
    List<Client> findByActive(Boolean active);
    Optional<Client> findByDocument(String document);
    void deleteById(Long id);
    boolean existsById(Long id);
}