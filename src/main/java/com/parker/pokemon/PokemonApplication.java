package com.parker.pokemon;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.parker.pokemon.model.Pokemon;
import com.parker.pokemon.model.PokemonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class PokemonApplication {

	private static final Logger log = LoggerFactory.getLogger(PokemonApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PokemonApplication.class, args);
	}

}