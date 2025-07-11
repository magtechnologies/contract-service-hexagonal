package com.magtech.contractservice.infrastructure.config;

import com.magtech.contractservice.domain.port.out.ClientRepository;
import com.magtech.contractservice.domain.port.out.ContractRepository;
import com.magtech.contractservice.domain.service.ClientService;
import com.magtech.contractservice.domain.service.ContractService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ContractService contractService(ContractRepository contractRepository,
                                           ClientRepository clientRepository) {
        return new ContractService(contractRepository, clientRepository);
    }

    @Bean
    public ClientService clientService(ClientRepository clientRepository) {
        return new ClientService(clientRepository);
    }
}