package com.minsait.repositories;

import com.minsait.models.Pokedex;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPokedexRepository extends JpaRepository<Pokedex, Long> {

}
