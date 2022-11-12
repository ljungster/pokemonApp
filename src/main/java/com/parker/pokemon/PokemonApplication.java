package com.parker.pokemon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class PokemonApplication {

	private static final Logger log = LoggerFactory.getLogger(PokemonApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PokemonApplication.class, args);
	}

}
