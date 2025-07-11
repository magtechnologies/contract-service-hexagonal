package com.magtech.contractservice.infrastructure.adapter.in.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Client response")
public class ClientResponse {

    @Schema(description = "Client ID", example = "1")
    private Long id;

    @Schema(description = "Client name", example = "John Doe")
    private String name;

    @Schema(description = "Client document", example = "12345678901")
    private String document;

    @Schema(description = "Client email", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Client phone", example = "+1234567890")
    private String phone;

    @Schema(description = "Client address", example = "123 Main St, City, State")
    private String address;

    @Schema(description = "Client active status", example = "true")
    private Boolean active;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Creation date", example = "2024-01-01")
    private LocalDate createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "Last update date", example = "2024-01-01")
    private LocalDate updatedAt;
}