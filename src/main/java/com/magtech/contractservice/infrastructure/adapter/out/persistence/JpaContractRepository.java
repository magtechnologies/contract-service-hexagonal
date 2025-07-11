package com.magtech.contractservice.infrastructure.adapter.out.persistence;

import com.magtech.contractservice.infrastructure.adapter.out.persistence.entity.ContractEntity;
import com.magtech.contractservice.infrastructure.adapter.out.persistence.entity.ContractStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaContractRepository extends JpaRepository<ContractEntity, Long> {

    List<ContractEntity> findByStatus(ContractStatusEntity status);

    @Query("SELECT c FROM ContractEntity c WHERE c.client.id = :clientId")
    List<ContractEntity> findByClientId(@Param("clientId") Long clientId);
}