package com.shankulk.service.impl;

import com.shankulk.web.PokeApiClient;
import com.shankulk.domain.Pokemon;
import com.shankulk.service.PokemonService;
import com.shankulk.service.TranslationService;
import org.springframework.stereotype.Service;

@Service
public class PokemonServiceImpl implements PokemonService {

    private final PokeApiClient restClient;
    private final TranslationService translationService;

    public PokemonServiceImpl(PokeApiClient restClient, TranslationService translationService) {
        this.restClient = restClient;
        this.translationService = translationService;
    }

    @Override
    public Pokemon getPokemonByName(String name) {
        return restClient.fetchPokemon(name);
    }

    @Override
    public Pokemon getTranslatedPokemon(String name) {
        Pokemon pokemon = restClient.fetchPokemon(name);
        final String translatedDescription = translationService
                .getTranslatedDescription(pokemon.getDescription(), pokemon.getHabitat());
        pokemon.applyTranslation(translatedDescription);

        return pokemon;
    }
}
