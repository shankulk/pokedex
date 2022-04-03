package com.shankulk.web;

import com.shankulk.PokeApiRestClient;
import com.shankulk.domain.Pokemon;
import com.shankulk.service.PokemonService;
import com.shankulk.service.TranslationService;
import com.shankulk.service.impl.PokemonServiceImpl;
import com.shankulk.service.impl.TranslationServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class PokemonControllerWebMvcTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    @Qualifier(value="translationRestTemplate")
    private RestTemplate translationRestTemplate;

    @MockBean
    @Qualifier(value="pokeApiRestTemplate")
    private RestTemplate pokeRestTemplate;

    @MockBean
    private PokeApiRestClient restClient;

    @SpyBean
    private PokemonServiceImpl pokemonService;

    @SpyBean
    private TranslationServiceImpl translationService;

    @BeforeEach
    private void setup() {
        Pokemon pokemon = new Pokemon();
        pokemon.setName("misty");
        BDDMockito.given(pokeRestTemplate.getForObject("", Pokemon.class)).willReturn(pokemon);
    }

    @Test
    @DisplayName("Verify Get Simple Pokemon returns a success response")
    void getSimplePokemon() throws Exception {
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.get("/pokemon/misty").header("accept", "application/json"))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(mvcResult.getResponse().getContentAsString()).contains("misty");
    }

    @Test
    @DisplayName("Verify Get Translated Pokemon returns a success response with translated description")
    void getTranslatedPokemon() throws Exception {
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.get("/pokemon/translated/misty").header("accept", "application/json"))
                .andExpect(status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        assertThat(response).contains("misty");
        assertThat(response).contains("translated");
    }

}