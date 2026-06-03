package com.biblioteca.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/")
    public String health() {
        return "Bem-vindo à API Biblioteca Digital! Acesse /swagger-ui.html para documentação.";
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "OK";
    }
}
