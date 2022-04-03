package com.shankulk.web;

import com.shankulk.domain.Pokemon;
import com.shankulk.dto.PokemonResponse;
import com.shankulk.service.PokemonService;
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

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping(value = "/{name}", produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<PokemonResponse> getSimplePokemon(@PathVariable("name") final String pokemonName) {
        Pokemon pokemon = pokemonService.getPokemonByName(pokemonName);
        return ResponseEntity.ok(new PokemonResponse(pokemon));
    }

    @GetMapping(value = "/translated/{name}", produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<PokemonResponse> getTranslatedPokemon(@PathVariable("name") final String pokemonName) {

        return ResponseEntity.ok(new PokemonResponse(pokemonService.getTranslatedPokemon(pokemonName)));
    }


}
