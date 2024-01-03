package com.minsait.repositories;

import com.minsait.models.PokemonFound;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPokemonFoundRepository extends JpaRepository<PokemonFound, Long> {
    List<PokemonFound> findAllByPokedexId(Long idPokedex);
    List<PokemonFound> findByIdPokemon(Long idPokemon);
}
