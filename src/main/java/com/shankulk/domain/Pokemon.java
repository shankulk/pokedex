package com.shankulk.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {

    private String name;

    @JsonProperty("is_legendary")
    private boolean legendary;

    private String habitat;

    @JsonProperty("flavor_text_entries")
    private List<Description> descriptions;

    public Optional<Description> getEnglishDescription() {
        return descriptions.stream().filter(description -> "en".equalsIgnoreCase(description.getLanguage().getName())).findAny();
    }
}
