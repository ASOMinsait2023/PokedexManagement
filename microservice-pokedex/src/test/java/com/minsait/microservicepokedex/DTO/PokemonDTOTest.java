package com.minsait.microservicepokedex.DTO;

import com.minsait.DTO.PokemonDTO;
import com.minsait.DTO.TypeDTO;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PokemonDTOTest {

    @Test
    public void testPokemonDTOStructure() {
        TypeDTO.Type type = new TypeDTO.Type("Electric");
        TypeDTO typeDTO = new TypeDTO(1, type);

        PokemonDTO pokemonDTO = new PokemonDTO(1L, "Pikachu", List.of(typeDTO), 60);

        assertThat(pokemonDTO.getId()).isEqualTo(1L);
        assertThat(pokemonDTO.getName()).isEqualTo("Pikachu");
        assertThat(pokemonDTO.getTypes()).hasSize(1);
        assertThat(pokemonDTO.getTypes().get(0).getSlot()).isEqualTo(1);
        assertThat(pokemonDTO.getTypes().get(0).getType().getName()).isEqualTo("Electric");
        assertThat(pokemonDTO.getWeight()).isEqualTo(60);
    }
}