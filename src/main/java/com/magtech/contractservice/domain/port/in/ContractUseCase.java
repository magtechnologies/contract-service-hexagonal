package com.magtech.contractservice.domain.port.in;

import com.magtech.contractservice.domain.model.Contract;
import com.magtech.contractservice.domain.model.ContractStatus;

import java.util.List;
import java.util.Optional;

public interface ContractUseCase {
    Contract createContract(Contract contract);
    Optional<Contract> findById(Long id);
    List<Contract> findAll();
    List<Contract> findByStatus(ContractStatus status);
    List<Contract> findByClientId(Long clientId);
    Contract updateContract(Long id, Contract contract);
    void deleteContract(Long id);
}