package com.magtech.contractservice.shared.mapper;

import com.magtech.contractservice.domain.model.Client;
import com.magtech.contractservice.domain.model.Contract;
import com.magtech.contractservice.infrastructure.adapter.in.web.dto.ContractRequest;
import com.magtech.contractservice.infrastructure.adapter.in.web.dto.ContractResponse;
import com.magtech.contractservice.infrastructure.adapter.out.persistence.entity.ContractEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = ClientMapper.class)
public interface ContractMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "client", source = "clientId", qualifiedByName = "clientIdToClient")
    Contract toDomain(ContractRequest request);

    ContractResponse toResponse(Contract contract);

    @Mapping(target = "client", source = "client")
    ContractEntity toEntity(Contract contract);

    @Mapping(target = "client", source = "client")
    Contract toDomain(ContractEntity entity);

    @Named("clientIdToClient")
    default Client clientIdToClient(Long clientId) {
        if (clientId == null) {
            return null;
        }
        return Client.builder().id(clientId).build();
    }
}