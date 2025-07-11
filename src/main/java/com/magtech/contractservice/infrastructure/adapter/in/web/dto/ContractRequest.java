package com.magtech.contractservice.infrastructure.adapter.in.web.dto;

import com.magtech.contractservice.domain.model.ContractStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Contract creation/update request")
public class ContractRequest {

    @NotBlank(message = "Contract number is required")
    @Schema(description = "Contract number", example = "CONT-2024-001")
    private String number;

    @NotBlank(message = "Description is required")
    @Schema(description = "Contract description", example = "Software development contract")
    private String description;

    @NotNull(message = "Value is required")
    @Positive(message = "Value must be positive")
    @Schema(description = "Contract value", example = "50000.00")
    private BigDecimal value;

    @NotNull(message = "Start date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Contract start date", example = "2024-01-01")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Contract end date", example = "2024-12-31")
    private LocalDate endDate;

    @Schema(description = "Contract status", example = "ACTIVE")
    private ContractStatus status;

    @NotNull(message = "Client ID is required")
    @Schema(description = "Client ID", example = "1")
    private Long clientId;
}