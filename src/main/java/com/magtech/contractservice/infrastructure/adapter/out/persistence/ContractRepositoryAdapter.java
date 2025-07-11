package com.magtech.contractservice.infrastructure.adapter.out.persistence;

import com.magtech.contractservice.domain.model.Contract;
import com.magtech.contractservice.domain.model.ContractStatus;
import com.magtech.contractservice.domain.port.out.ContractRepository;
import com.magtech.contractservice.infrastructure.adapter.out.persistence.entity.ContractEntity;
import com.magtech.contractservice.infrastructure.adapter.out.persistence.entity.ContractStatusEntity;
import com.magtech.contractservice.shared.mapper.ContractMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ContractRepositoryAdapter implements ContractRepository {

    private final JpaContractRepository jpaContractRepository;
    private final ContractMapper contractMapper;

    @Override
    public Contract save(Contract contract) {
        ContractEntity entity = contractMapper.toEntity(contract);
        ContractEntity savedEntity = jpaContractRepository.save(entity);
        return contractMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Contract> findById(Long id) {
        return jpaContractRepository.findById(id)
                .map(contractMapper::toDomain);
    }

    @Override
    public List<Contract> findAll() {
        return jpaContractRepository.findAll().stream()
                .map(contractMapper::toDomain)
                .toList();
    }

    @Override
    public List<Contract> findByStatus(ContractStatus status) {
        ContractStatusEntity statusEntity = ContractStatusEntity.valueOf(status.name());
        return jpaContractRepository.findByStatus(statusEntity).stream()
                .map(contractMapper::toDomain)
                .toList();
    }

    @Override
    public List<Contract> findByClientId(Long clientId) {
        return jpaContractRepository.findByClientId(clientId).stream()
                .map(contractMapper::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaContractRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return jpaContractRepository.existsById(id);
    }
}