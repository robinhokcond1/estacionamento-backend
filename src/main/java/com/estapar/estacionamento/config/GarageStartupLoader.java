package com.estapar.estacionamento.config;

import com.estapar.estacionamento.exception.GarageLoadException;
import com.estapar.estacionamento.service.GarageService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GarageStartupLoader {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ApplicationRunner initGarage(GarageService garageService) {
        return args -> {
            try {
                garageService.carregarDadosDaGaragem();
                System.out.println("[INFO] Dados da garagem carregados com sucesso.");
            } catch (GarageLoadException e) {
                System.err.println("[ERROR] Falha ao carregar dados da garagem: " + e.getMessage());
            }
        };
    }
}
