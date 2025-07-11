package com.magtech.contractservice.domain.service;

import com.magtech.contractservice.domain.model.Contract;
import com.magtech.contractservice.domain.model.ContractStatus;
import com.magtech.contractservice.domain.port.in.ContractUseCase;
import com.magtech.contractservice.domain.port.out.ClientRepository;
import com.magtech.contractservice.domain.port.out.ContractRepository;
import com.magtech.contractservice.shared.exception.ClientNotFoundException;
import com.magtech.contractservice.shared.exception.ContractNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContractService implements ContractUseCase {

    private final ContractRepository contractRepository;
    private final ClientRepository clientRepository;

    @Override
    public Contract createContract(Contract contract) {
        // Validar se cliente existe
        if (!clientRepository.existsById(contract.getClient().getId())) {
            throw new ClientNotFoundException("Client not found with id: " + contract.getClient().getId());
        }

        contract.setCreatedAt(LocalDate.now());
        contract.setUpdatedAt(LocalDate.now());

        if (contract.getStatus() == null) {
            contract.setStatus(ContractStatus.DRAFT);
        }

        return contractRepository.save(contract);
    }

    @Override
    public Optional<Contract> findById(Long id) {
        return contractRepository.findById(id);
    }

    @Override
    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    @Override
    public List<Contract> findByStatus(ContractStatus status) {
        return contractRepository.findByStatus(status);
    }

    @Override
    public List<Contract> findByClientId(Long clientId) {
        return contractRepository.findByClientId(clientId);
    }

    @Override
    public Contract updateContract(Long id, Contract contract) {
        Contract existingContract = contractRepository.findById(id)
                .orElseThrow(() -> new ContractNotFoundException("Contract not found with id: " + id));

        // Validar se cliente existe
        if (!clientRepository.existsById(contract.getClient().getId())) {
            throw new ClientNotFoundException("Client not found with id: " + contract.getClient().getId());
        }

        contract.setId(id);
        contract.setCreatedAt(existingContract.getCreatedAt());
        contract.setUpdatedAt(LocalDate.now());

        return contractRepository.save(contract);
    }

    @Override
    public void deleteContract(Long id) {
        if (!contractRepository.existsById(id)) {
            throw new ContractNotFoundException("Contract not found with id: " + id);
        }
        contractRepository.deleteById(id);
    }
}