package com.minsait.services;

import com.minsait.models.Pokedex;

import java.util.List;

public interface IPokedexService {
    List<Pokedex> findAll();
    Pokedex findById(Long id);
    void savePokedex(Pokedex pokedex);
//    List<Pokedex> findByTrainerId(Long idTrainer);
}
