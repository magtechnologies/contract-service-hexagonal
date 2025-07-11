package com.magtech.contractservice.infrastructure.adapter.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Client creation/update request")
public class ClientRequest {

    @NotBlank(message = "Name is required")
    @Schema(description = "Client name", example = "John Doe")
    private String name;

    @NotBlank(message = "Document is required")
    @Schema(description = "Client document", example = "12345678901")
    private String document;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Schema(description = "Client email", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Client phone", example = "+1234567890")
    private String phone;

    @Schema(description = "Client address", example = "123 Main St, City, State")
    private String address;

    @Schema(description = "Client active status", example = "true")
    private Boolean active;
}