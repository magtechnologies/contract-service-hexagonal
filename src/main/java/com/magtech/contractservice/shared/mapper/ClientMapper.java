package com.magtech.contractservice.shared.mapper;

import com.magtech.contractservice.domain.model.Client;
import com.magtech.contractservice.infrastructure.adapter.in.web.dto.ClientRequest;
import com.magtech.contractservice.infrastructure.adapter.in.web.dto.ClientResponse;
import com.magtech.contractservice.infrastructure.adapter.out.persistence.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contracts", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Client toDomain(ClientRequest request);

    @Mapping(target = "contracts", ignore = true)
    ClientResponse toResponse(Client client);

    @Mapping(target = "contracts", ignore = true)
    ClientEntity toEntity(Client client);

    @Mapping(target = "contracts", ignore = true)
    Client toDomain(ClientEntity entity);
}