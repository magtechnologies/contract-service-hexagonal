package com.magtech.contractservice.infrastructure.adapter.out.persistence;

import com.magtech.contractservice.infrastructure.adapter.out.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaClientRepository extends JpaRepository<ClientEntity, Long> {

    List<ClientEntity> findByActive(Boolean active);

    Optional<ClientEntity> findByDocument(String document);
}