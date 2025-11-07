package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Active la configuration automatique, le scan des entités, contrôleurs, services, etc.
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        System.out.println("🚀 Application Spring Boot démarrée avec succès !");
    }
}
