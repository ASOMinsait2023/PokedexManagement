package com.minsait.services;

import com.minsait.models.Pokemon;
import com.minsait.repositories.IPokemonRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PokemonServiceImplements implements IPokemonService{
    @Autowired
    IPokemonRepository pokemonRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Pokemon> findAll() {
        return pokemonRepository.findAll();
    }

    @Override
    public Pokemon findByPokedexId(Long id) {
        return pokemonRepository.findById(id).orElseThrow();
    }

    @Override
    public Pokemon saveFound(Pokemon pokemon) {
        boolean IsAlreadyFound = pokemonRepository.findAll().stream().anyMatch(pokemon1 -> pokemon1.getIdPokemon().equals(pokemon.getIdPokemon()));
        if(IsAlreadyFound)return pokemonRepository.save(pokemon);

        throw new EntityExistsException("Pokemon with id " + pokemon.getIdPokemon() + " is already found.");
    }

    @Override
    public List<Pokemon> findAllFoundByPokedexId(Long idPokedex) {
        return pokemonRepository.findAllFoundByPokedexId(idPokedex);
    }
}
