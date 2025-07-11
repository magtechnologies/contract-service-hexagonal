package com.magtech.contractservice.infrastructure.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.magtech.contractservice.domain.model.ContractStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Contract response")
public class ContractResponse {

    @Schema(description = "Contract ID", example = "1")
    private Long id;

    @Schema(description = "Contract number", example = "CONT-2024-001")
    private String number;

    @Schema(description = "Contract description", example = "Software development contract")
    private String description;

    @Schema(description = "Contract value", example = "50000.00")
    private BigDecimal value;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Contract start date", example = "2024-01-01")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Contract end date", example = "2024-12-31")
    private LocalDate endDate;

    @Schema(description = "Contract status", example = "ACTIVE")
    private ContractStatus status;

    @Schema(description = "Client information")
    private ClientResponse client;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Creation date", example = "2024-01-01")
    private LocalDate createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Last update date", example = "2024-01-01")
    private LocalDate updatedAt;
}