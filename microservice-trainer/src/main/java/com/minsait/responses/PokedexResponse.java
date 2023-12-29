package com.minsait.responses;

import com.minsait.DTO.PokemonDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PokedexResponse {
    private Long id;
    private String region;
    private String description;
    private String trainerNotes;
    private List<PokemonDTO> pokemons;
}
