package com.magtech.contractservice.domain.port.out;

import com.magtech.contractservice.domain.model.Contract;
import com.magtech.contractservice.domain.model.ContractStatus;

import java.util.List;
import java.util.Optional;

public interface ContractRepository {
    Contract save(Contract contract);
    Optional<Contract> findById(Long id);
    List<Contract> findAll();
    List<Contract> findByStatus(ContractStatus status);
    List<Contract> findByClientId(Long clientId);
    void deleteById(Long id);
    boolean existsById(Long id);
}