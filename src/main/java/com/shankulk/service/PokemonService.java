package com.shankulk.service;

import com.shankulk.domain.Pokemon;

public interface PokemonService {
    Pokemon getPokemonByName(String name);

    Pokemon getTranslatedPokemon(String name);
}
