package com.shankulk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Translation {

    private Contents contents;

    @JsonIgnore
    public String translation() {
        return contents.translated;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Contents {
        private String translated;
    }
}
