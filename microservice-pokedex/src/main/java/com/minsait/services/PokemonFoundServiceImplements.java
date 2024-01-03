package com.minsait.services;

import com.minsait.models.PokemonFound;
import com.minsait.repositories.IPokedexRepository;
import com.minsait.repositories.IPokemonFoundRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.ranges.RangeException;

import java.util.List;

@Service
public class PokemonFoundServiceImplements implements IPokemonFoundService {
    @Autowired
    IPokemonFoundRepository pokemonRepository;

    @Autowired
    IPokedexRepository pokedexRepository;


    @Override
    public List<PokemonFound> findAllByPokedexId(Long id) {
        return pokemonRepository.findAllByPokedexId(id);
    }

    @Override
    public List<PokemonFound> findByIdPokemon(Long id){
        return pokemonRepository.findByIdPokemon(id);
    }

    @Override
    public PokemonFound save(PokemonFound pokemonFound) {
        boolean existsPokedex = pokedexRepository.existsById(pokemonFound.getPokedex().getId());
        boolean isAlreadyFound =false;

        boolean outOfRange = pokemonFound.getIdPokemon()<1|pokemonFound.getIdPokemon()>10;
        if (outOfRange)throw new RangeException
                (RangeException.BAD_BOUNDARYPOINTS_ERR, "Pokemon with id " + pokemonFound.getIdPokemon() + " Is out of range");

        if (existsPokedex) {
            isAlreadyFound = pokemonRepository.findAll()
                    .stream()
                    .filter(pokemonFound1 -> pokemonFound1.getPokedex().getId().equals(pokemonFound.getPokedex().getId()))
                    .anyMatch(pokemonFound1 -> pokemonFound1.getIdPokemon()
                    .equals(pokemonFound.getIdPokemon()));

            if (isAlreadyFound) throw new EntityExistsException
                    ("Pokemon with id " + pokemonFound.getIdPokemon() + " Already exists.");

            return pokemonRepository.save(pokemonFound);
        }

        throw new EntityExistsException("Pokedex does not exist.");

    }

}
