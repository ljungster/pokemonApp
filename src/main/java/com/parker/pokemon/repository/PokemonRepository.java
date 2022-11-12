package com.parker.pokemon.repository;

import com.parker.pokemon.model.Pokemon;
import org.springframework.data.repository.CrudRepository;

public interface PokemonRepository extends CrudRepository<Pokemon, Integer> {
    Pokemon findPokemonById(Integer id);
}
