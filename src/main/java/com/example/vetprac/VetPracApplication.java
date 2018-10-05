package com.example.vetprac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VetPracApplication {

	public static void main(String[] args) {
		SpringApplication.run(VetPracApplication.class, args);
	}
}
