package com.parker.pokemon.controller;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.parker.pokemon.PokemonApplication;
import com.parker.pokemon.model.Pokemon;
import com.parker.pokemon.model.PokemonResponse;
import com.parker.pokemon.repository.PokemonRepository;
 import org.json.simple.JSONObject;
 import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Set;

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
    private void loadPokemon() throws JsonProcessingException {
        String url = "https://pokeapi.co/api/v2/pokemon?limit=20&offset=0";
        RestTemplate restTemplate = new RestTemplate();
        PokemonResponse resp = restTemplate.getForObject(url, PokemonResponse.class);
        assert resp != null;
        int id = 1;
        for (PokemonResponse.PokemonItem item : resp.getResults()) {
            String json = restTemplate.getForObject(item.getUrl(), String.class);
            assert json != null;
            JSONObject object = (JSONObject) JSONValue.parse(json);
            JSONObject sprites = (JSONObject) object.get("sprites");
            JSONObject other = (JSONObject) sprites.get("other");
            JSONObject officialArtwork = (JSONObject) other.get("official-artwork");
            String name = object.get("name").toString();
            String imageURL = officialArtwork.get("front_default").toString();
            int height = Integer.parseInt(object.get("height").toString());
            int weight = Integer.parseInt(object.get("weight").toString());
            Pokemon pokemon = new Pokemon();
            pokemon.setName(name);
            pokemon.setId(id);
            pokemon.setHeight(height);
            pokemon.setWeight(weight);
            pokemon.setImageURL(imageURL);
            pokemonRepository.save(pokemon);
            id++;
//            System.out.printf("%d || name: %s, height: %d, weight: %d, url: %s\n", pokemon.getId(), pokemon.getName(), pokemon.getHeight(), pokemon.getWeight(), pokemon.getImageURL());
        }
    }
}
