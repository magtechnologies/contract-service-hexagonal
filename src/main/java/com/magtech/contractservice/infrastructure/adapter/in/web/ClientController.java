package com.magtech.contractservice.infrastructure.adapter.in.web;

import com.magtech.contractservice.domain.port.in.ClientUseCase;
import com.magtech.contractservice.infrastructure.adapter.in.web.dto.ClientRequest;
import com.magtech.contractservice.infrastructure.adapter.in.web.dto.ClientResponse;
import com.magtech.contractservice.shared.exception.ClientNotFoundException;
import com.magtech.contractservice.shared.mapper.ClientMapper;
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
@RequestMapping("/clients")
@RequiredArgsConstructor
@Tag(name = "Clients", description = "Client management operations")
public class ClientController {

    private final ClientUseCase clientUseCase;
    private final ClientMapper clientMapper;

    @PostMapping
    @Operation(summary = "Create a new client")
    public ResponseEntity<ClientResponse> createClient(@Valid @RequestBody ClientRequest request) {
        var client = clientMapper.toDomain(request);
        var createdClient = clientUseCase.createClient(client);
        var response = clientMapper.toResponse(createdClient);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get client by ID")
    public ResponseEntity<ClientResponse> getClient(
            @Parameter(description = "Client ID") @PathVariable Long id) {
        return clientUseCase.findById(id)
                .map(client -> ResponseEntity.ok(clientMapper.toResponse(client)))
                .orElseThrow(() -> new ClientNotFoundException("Client not found with id: " + id));
    }

    @GetMapping
    @Operation(summary = "Get all clients")
    public ResponseEntity<List<ClientResponse>> getAllClients(
            @Parameter(description = "Filter by active status") @RequestParam(required = false) Boolean active) {

        List<ClientResponse> clients;

        if (active != null) {
            clients = clientUseCase.findByActive(active).stream()
                    .map(clientMapper::toResponse)
                    .toList();
        } else {
            clients = clientUseCase.findAll().stream()
                    .map(clientMapper::toResponse)
                    .toList();
        }

        return ResponseEntity.ok(clients);
    }

    @GetMapping("/document/{document}")
    @Operation(summary = "Get client by document")
    public ResponseEntity<ClientResponse> getClientByDocument(
            @Parameter(description = "Client document") @PathVariable String document) {
        return clientUseCase.findByDocument(document)
                .map(client -> ResponseEntity.ok(clientMapper.toResponse(client)))
                .orElseThrow(() -> new ClientNotFoundException("Client not found with document: " + document));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update client")
    public ResponseEntity<ClientResponse> updateClient(
            @Parameter(description = "Client ID") @PathVariable Long id,
            @Valid @RequestBody ClientRequest request) {
        var client = clientMapper.toDomain(request);
        var updatedClient = clientUseCase.updateClient(id, client);
        var response = clientMapper.toResponse(updatedClient);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete client")
    public ResponseEntity<Void> deleteClient(
            @Parameter(description = "Client ID") @PathVariable Long id) {
        clientUseCase.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}