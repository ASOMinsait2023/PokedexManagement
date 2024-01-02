package com.minsait.repositories;

import com.minsait.models.Pokedex;
import com.minsait.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPokemonRepository extends JpaRepository<Pokemon, Long> {
    List<Pokemon> findAllFoundByPokedexId(Long pokedexId);
}
