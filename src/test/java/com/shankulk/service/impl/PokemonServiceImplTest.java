package com.shankulk.service.impl;

import com.shankulk.domain.Pokemon;
import com.shankulk.web.PokeApiClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.shankulk.helper.TestHelper.buildPokemon;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class PokemonServiceImplTest {

    @InjectMocks
    private PokemonServiceImpl pokemonService;

    @Mock
    private PokeApiClient restClient;

    @Test
    void getPokemon() {
        final Pokemon pokemon = buildPokemon();
        Mockito.when(restClient.fetchPokemon(anyString())).thenReturn(pokemon);

        Pokemon misty = pokemonService.getPokemonByName("misty");

        Assertions.assertThat(misty).isNotNull();
        Assertions.assertThat(misty.getName()).isEqualTo("misty");
    }

}