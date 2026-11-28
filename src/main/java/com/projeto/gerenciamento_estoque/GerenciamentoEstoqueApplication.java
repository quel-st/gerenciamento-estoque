package com.projeto.gerenciamento_estoque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication(scanBasePackages = "com.projeto")
@EntityScan(basePackages = "com.projeto.model")
public class GerenciamentoEstoqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciamentoEstoqueApplication.class, args);
	}

}
