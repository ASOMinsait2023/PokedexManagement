package com.minsait.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PokedexDTO {
    private Long id;
    private String region;
    private String description;
    private String trainerNotes;
    private List<PokemonDTO> pokemons;
}
