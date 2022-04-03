package com.shankulk.web;

import com.shankulk.domain.Description;
import com.shankulk.domain.Pokemon;
import com.shankulk.service.impl.PokemonServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class PokemonControllerWebMvcTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PokemonServiceImpl pokemonService;

    @Test
    @DisplayName("Verify Get Simple Pokemon returns a success response")
    void getSimplePokemon() throws Exception {
        Pokemon pokemon = buildPokemon("description");
        Mockito.when(pokemonService.getPokemonByName(anyString())).thenReturn(pokemon);

        MvcResult result = mvc
                .perform(MockMvcRequestBuilders.get("/pokemon/misty").header("accept", "application/json"))
                .andExpect(status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();
        assertThat(response).contains("misty");
        assertThat(response).contains("description");
    }

    @Test
    @DisplayName("Verify Get Translated Pokemon returns a success response with translated description")
    void getTranslatedPokemon() throws Exception {
        Mockito.when(pokemonService.getTranslatedPokemon(anyString())).thenReturn(buildPokemon("translated"));

        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.get("/pokemon/translated/misty").header("accept", "application/json"))
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertThat(response).contains("misty");
        assertThat(response).contains("translated");
    }

    private static Pokemon buildPokemon(final String desc) {
        Description.Language language = new Description.Language();
        language.setName("en");

        Description description = new Description();
        description.setDescription(desc);
        description.setLanguage(language);

        Pokemon pokemon = new Pokemon();
        pokemon.setName("misty");
        pokemon.setLegendary(false);
        pokemon.setDescriptions(List.of(description));
        return pokemon;
    }

}