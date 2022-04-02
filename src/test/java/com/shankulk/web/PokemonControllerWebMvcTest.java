package com.shankulk.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class PokemonControllerWebMvcTest {

    @Autowired
    private MockMvc mvc;

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