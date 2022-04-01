package com.shankulk.dto;

import lombok.Data;

@Data
public class PokemonResponse {

    private final String name;
    private final String description;
    private final String habitat;
    private final boolean isLegendary;
}
