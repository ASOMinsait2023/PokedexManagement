package com.minsait.services;

import com.minsait.models.Pokedex;
import com.minsait.responses.PokemonByIdResponse;

import java.util.List;

public interface IPokedexService {
    List<Pokedex> findAll();
    Pokedex findById(Long id);
    Pokedex save(Pokedex pokedex);
    PokemonByIdResponse findPokemonByPokedexId(Long idPokedex);
}
