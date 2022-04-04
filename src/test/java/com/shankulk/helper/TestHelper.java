package com.shankulk.helper;

import com.shankulk.domain.Description;
import com.shankulk.domain.Habitat;
import com.shankulk.domain.Pokemon;
import com.shankulk.domain.Translation;

import java.util.List;

public class TestHelper {

    private TestHelper() {
    }

    public static Pokemon buildPokemon() {
        Pokemon pokemon = new Pokemon();
        pokemon.setLegendary(false);
        pokemon.setName("misty");
        Habitat habitat = buildHabitat();
        pokemon.setHabitat(habitat);

        Description description = buildDescription();
        pokemon.setDescriptions(List.of(description));

        return pokemon;
    }

    public static Description buildDescription() {
        Description description = new Description();
        description.setDescription("Funny pokemon description");
        description.setLanguage(buildLanguage("en"));
        return description;
    }

    public static Description.Language buildLanguage(String lang) {
        Description.Language language = new Description.Language();
        language.setName(lang);
        return language;
    }

    public static Habitat buildHabitat() {
        Habitat habitat = new Habitat();
        habitat.setName("cave");
        return habitat;
    }

    public static Translation buildTranslation() {
        Translation translation = new Translation();
        translation.setContents(buildContents());
        return translation;
    }

    private static Translation.Contents buildContents() {
        Translation.Contents contents = new Translation.Contents();
        contents.setTranslated("translated description");
        return contents;
    }
}
