package com.shankulk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {

    private String name;

    @JsonProperty("is_legendary")
    private boolean legendary;

    private Habitat habitat;

    @JsonIgnore
    private String description;

    @Getter(AccessLevel.NONE)
    @JsonProperty("flavor_text_entries")
    private List<Description> descriptions;

    public String getDescription() {
        return descriptions
                .stream()
                .filter(description -> "en".equalsIgnoreCase(description.getLanguage().getName()))
                .findAny().get()
                .getDescription();
    }

    public void applyTranslation(final String translatedDescription) {
        this.setDescription(translatedDescription);
    }
}
