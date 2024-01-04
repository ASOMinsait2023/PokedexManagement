package com.minsait.microservicepokedex.models;

import com.minsait.models.Pokedex;
import com.minsait.models.Region;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PokedexTest {

    @Test
    public void testPokedexStructure() {

        Pokedex pokedex = new Pokedex(1L, Region.Kanto, "Kanto region Pokedex", "Some trainer notes", null);


        assertThat(pokedex.getId()).isEqualTo(1L);
        assertThat(pokedex.getRegion()).isEqualTo(Region.Kanto);
        assertThat(pokedex.getDescription()).isEqualTo("Kanto region Pokedex");
        assertThat(pokedex.getTrainerNotes()).isEqualTo("Some trainer notes");

    }
}
