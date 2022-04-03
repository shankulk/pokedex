package com.shankulk.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Description {

    @JsonProperty("flavor_text")
    private String description;

    private Language language;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    class Language {

        private String name;
    }

}
