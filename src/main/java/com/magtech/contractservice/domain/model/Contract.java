package com.magtech.contractservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    private Long id;
    private String number;
    private String description;
    private BigDecimal value;
    private LocalDate startDate;
    private LocalDate endDate;
    private ContractStatus status;
    private Client client;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
