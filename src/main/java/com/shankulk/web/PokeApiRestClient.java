package com.shankulk.web;

import com.shankulk.domain.Pokemon;
import com.shankulk.exception.BadRequestException;
import com.shankulk.exception.NoPokemonFoundException;
import com.shankulk.exception.ServerErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import static com.shankulk.config.CacheConfig.POKEMON;

@Slf4j
@Component
public class PokeApiRestClient {

    private final RestTemplate restTemplate;

    public PokeApiRestClient(@Qualifier("pokeApiRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable(cacheNames = POKEMON, key = "#name", unless = "#result == null")
    public Pokemon fetchPokemon(String name) {
        try {
            return restTemplate.getForObject("/pokemon-species/{name}", Pokemon.class, name);
        } catch (HttpClientErrorException e) {
            if (e.getRawStatusCode() == 404) {
                log.error("Not found", e);
                throw new NoPokemonFoundException(String.format("Pokemon %s not found", name));
            }
            log.error("Bad request", e);
            throw new BadRequestException("Invalid request");
        } catch (HttpServerErrorException e) {
            log.error("Server error", e);
            throw new ServerErrorException("Internal server error occurred.");
        }
    }
}
