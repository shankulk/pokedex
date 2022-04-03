package com.shankulk.dto;

import com.shankulk.domain.Pokemon;
import lombok.Data;

@Data
public class PokemonResponse {

    private final String name;
    private final String description;
    private final String habitat;
    private final boolean isLegendary;

    public PokemonResponse(Pokemon pokemon) {
        name = pokemon.getName();
        description = pokemon.getDescription();
        habitat = pokemon.getHabitat();
        isLegendary = pokemon.isLegendary();
    }
}
