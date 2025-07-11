package com.magtech.contractservice.infrastructure.adapter.in.web;

import com.magtech.contractservice.domain.model.ContractStatus;
import com.magtech.contractservice.domain.port.in.ContractUseCase;
import com.magtech.contractservice.infrastructure.adapter.in.web.dto.ContractRequest;
import com.magtech.contractservice.infrastructure.adapter.in.web.dto.ContractResponse;
import com.magtech.contractservice.shared.exception.ContractNotFoundException;
import com.magtech.contractservice.shared.mapper.ContractMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
@RequiredArgsConstructor
@Tag(name = "Contracts", description = "Contract management operations")
public class ContractController {

    private final ContractUseCase contractUseCase;
    private final ContractMapper contractMapper;

    @PostMapping
    @Operation(summary = "Create a new contract")
    public ResponseEntity<ContractResponse> createContract(@Valid @RequestBody ContractRequest request) {
        var contract = contractMapper.toDomain(request);
        var createdContract = contractUseCase.createContract(contract);
        var response = contractMapper.toResponse(createdContract);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get contract by ID")
    public ResponseEntity<ContractResponse> getContract(
            @Parameter(description = "Contract ID") @PathVariable Long id) {
        return contractUseCase.findById(id)
                .map(contract -> ResponseEntity.ok(contractMapper.toResponse(contract)))
                .orElseThrow(() -> new ContractNotFoundException("Contract not found with id: " + id));
    }

    @GetMapping
    @Operation(summary = "Get all contracts")
    public ResponseEntity<List<ContractResponse>> getAllContracts(
            @Parameter(description = "Filter by status") @RequestParam(required = false) ContractStatus status,
            @Parameter(description = "Filter by client ID") @RequestParam(required = false) Long clientId) {

        List<ContractResponse> contracts;

        if (status != null) {
            contracts = contractUseCase.findByStatus(status).stream()
                    .map(contractMapper::toResponse)
                    .toList();
        } else if (clientId != null) {
            contracts = contractUseCase.findByClientId(clientId).stream()
                    .map(contractMapper::toResponse)
                    .toList();
        } else {
            contracts = contractUseCase.findAll().stream()
                    .map(contractMapper::toResponse)
                    .toList();
        }

        return ResponseEntity.ok(contracts);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update contract")
    public ResponseEntity<ContractResponse> updateContract(
            @Parameter(description = "Contract ID") @PathVariable Long id,
            @Valid @RequestBody ContractRequest request) {
        var contract = contractMapper.toDomain(request);
        var updatedContract = contractUseCase.updateContract(id, contract);
        var response = contractMapper.toResponse(updatedContract);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete contract")
    public ResponseEntity<Void> deleteContract(
            @Parameter(description = "Contract ID") @PathVariable Long id) {
        contractUseCase.deleteContract(id);
        return ResponseEntity.noContent().build();
    }
}