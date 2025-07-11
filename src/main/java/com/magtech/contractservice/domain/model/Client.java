package com.magtech.contractservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    private Long id;
    private String name;
    private String document;
    private String email;
    private String phone;
    private String address;
    private Boolean active;
    private List<Contract> contracts;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}