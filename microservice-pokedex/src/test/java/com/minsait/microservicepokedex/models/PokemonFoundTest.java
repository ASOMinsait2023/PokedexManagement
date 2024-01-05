package com.minsait.microservicepokedex.models;

import com.minsait.models.PokemonFound;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class PokemonFoundTest {

    @Test
    public void testPokemonFoundStructure() {

        PokemonFound pokemonFound = new PokemonFound(1L, 25L, null);

        assertThat(pokemonFound.getId()).isEqualTo(1L);
        assertThat(pokemonFound.getIdPokemon()).isEqualTo(25L);
    }
}
