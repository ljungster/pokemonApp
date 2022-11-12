package com.parker.pokemon.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties
public class PokemonResponse {

    public static class PokemonItem {
        String name;
        String url;

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }
    }
    private List<PokemonItem> results;

    public List<PokemonItem> getResults() {
        return results;
    }
}
