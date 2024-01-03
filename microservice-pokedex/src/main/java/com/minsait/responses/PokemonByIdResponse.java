package com.minsait.responses;

import com.minsait.DTO.PokemonDTO;
import com.minsait.models.PokemonFound;
import com.minsait.models.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PokemonByIdResponse {
    Long id;
    Region region;
    List<PokemonDTO> pokemonsInRegion;
    List<PokemonDTO> pokemonFoundList;
}
