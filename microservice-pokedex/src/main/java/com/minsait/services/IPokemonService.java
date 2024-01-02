package com.minsait.services;

import com.minsait.models.Pokedex;
import com.minsait.models.Pokemon;
import com.minsait.responses.PokemonByIdResponse;

import java.util.List;

public interface IPokemonService {

    List<Pokemon> findAll();
    Pokemon findByPokedexId(Long id);
    Pokemon saveFound(Pokemon pokemon);
    List<Pokemon> findAllFoundByPokedexId(Long idPokedex);
}
