package com.minsait.DTO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PokedexDTO {
    private Long id;
    private String region;
    private String description;
    private String trainerNotes;
    private List<PokemonFoundDTO> pokemonFound;
}
