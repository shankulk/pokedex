package com.shankulk.web;

import com.shankulk.dto.PokemonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @GetMapping(value = "/{name}", produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<PokemonResponse> getSimplePokemon(@PathVariable("name") final String pokemonName) {
        return ResponseEntity.ok(new PokemonResponse(pokemonName, "This is sample Pokemon", "Earth", false));
    }

    @GetMapping(value = "/translated/{name}", produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<PokemonResponse> getTranslatedPokemon(@PathVariable("name") final String pokemonName) {
        return ResponseEntity.ok(new PokemonResponse(pokemonName, "This is translated Pokemon", "Moon", true));
    }


}
