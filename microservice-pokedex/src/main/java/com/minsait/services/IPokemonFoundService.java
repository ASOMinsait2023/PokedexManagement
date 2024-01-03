package com.minsait.services;

import com.minsait.models.PokemonFound;

import java.util.List;

public interface IPokemonFoundService {

    List<PokemonFound> findAllByPokedexId(Long id);
    List<PokemonFound> findByIdPokemon(Long id);
    PokemonFound save(PokemonFound pokemonFound);

}
