package com.magtech.contractservice.infrastructure.adapter.out.persistence;

import com.magtech.contractservice.domain.model.Client;
import com.magtech.contractservice.domain.port.out.ClientRepository;
import com.magtech.contractservice.infrastructure.adapter.out.persistence.entity.ClientEntity;
import com.magtech.contractservice.shared.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClientRepositoryAdapter implements ClientRepository {

    private final JpaClientRepository jpaClientRepository;
    private final ClientMapper clientMapper;

    @Override
    public Client save(Client client) {
        ClientEntity entity = clientMapper.toEntity(client);
        ClientEntity savedEntity = jpaClientRepository.save(entity);
        return clientMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Client> findById(Long id) {
        return jpaClientRepository.findById(id)
                .map(clientMapper::toDomain);
    }

    @Override
    public List<Client> findAll() {
        return jpaClientRepository.findAll().stream()
                .map(clientMapper::toDomain)
                .toList();
    }

    @Override
    public List<Client> findByActive(Boolean active) {
        return jpaClientRepository.findByActive(active).stream()
                .map(clientMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Client> findByDocument(String document) {
        return jpaClientRepository.findByDocument(document)
                .map(clientMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaClientRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaClientRepository.existsById(id);
    }
}