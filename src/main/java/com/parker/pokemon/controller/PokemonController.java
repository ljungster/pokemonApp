package com.parker.pokemon.controller;

import com.parker.pokemon.model.Pokemon;
import com.parker.pokemon.model.PokemonResponse;
import com.parker.pokemon.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@RestController
public class PokemonController {

    @Autowired
    private PokemonRepository pokemonRepository;

//    @PostMapping(value = "/add")
//    public void addPokemon(@RequestParam String name) {
//        Pokemon pokemon = new Pokemon();
//        pokemon.setName(name);
//        pokemonRepository.save(pokemon);
//    }

    @GetMapping(value = "/list")
    public Iterable<Pokemon> getAllPokemon() {
        return pokemonRepository.findAll();
    }

    @GetMapping(value = "find/{id}")
    public Pokemon findPokemonById(@PathVariable Integer id) {
        return pokemonRepository.findPokemonById(id);
    }

    @GetMapping(value = "/pokemon")
    public Object getPokemon() {
        String url = "https://pokeapi.co/api/v2/pokemon/";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, Object.class);
    }

    @PostConstruct
    private void loadPokemon() {
        String url = "https://pokeapi.co/api/v2/pokemon?limit=151&offset=0";
        RestTemplate restTemplate = new RestTemplate();
        PokemonResponse resp = restTemplate.getForObject(url, PokemonResponse.class);
        assert resp != null;
        for (PokemonResponse.PokemonItem item : resp.getResults()) {
//            System.out.println(item.getName());
            Pokemon pokemon = new Pokemon();
            pokemon.setName(item.getName());
            pokemonRepository.save(pokemon);
        }
//        System.out.println(resp.getResults());
    }
}
