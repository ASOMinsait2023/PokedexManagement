package com.minsait.repositories;

import com.minsait.models.Pokedex;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPokedexRepository extends JpaRepository<Pokedex, Long> {
//    List<Pokedex> findAllByTrainerId(Long pokedexId);
}
