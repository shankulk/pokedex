package com.shankulk.service.impl;

import com.shankulk.domain.Description;
import com.shankulk.domain.Habitat;
import com.shankulk.domain.Pokemon;
import com.shankulk.web.PokeApiRestClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class PokemonServiceImplTest {

    @InjectMocks
    private PokemonServiceImpl pokemonService;

    @Mock
    private PokeApiRestClient restClient;

    @Test
    void getPokemon() {
        final Pokemon pokemon = buildPokemon();
        Mockito.when(restClient.fetchPokemon(anyString())).thenReturn(pokemon);

        Pokemon misty = pokemonService.getPokemonByName("misty");

        Assertions.assertThat(misty).isNotNull();
        Assertions.assertThat(misty.getName()).isEqualTo("misty");
    }

    private static Pokemon buildPokemon() {
        Pokemon pokemon = new Pokemon();
        pokemon.setLegendary(false);
        pokemon.setName("misty");
        Habitat habitat = new Habitat();
        habitat.setName("cave");
        pokemon.setHabitat(habitat);

        Description description = new Description();
        description.setDescription("Funny pokemon description");
        Description.Language language = new Description.Language();
        language.setName("en");
        description.setLanguage(language);
        pokemon.setDescriptions(List.of(description));

        return pokemon;
    }

}