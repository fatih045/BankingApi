package com.example.banking_app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info= @Info(title = "Banking APi",
        description = "Banking Management System"))
public class BankingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingAppApplication.class, args);
    }

}
