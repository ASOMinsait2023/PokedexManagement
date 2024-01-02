package com.minsait.clients;

import com.minsait.DTO.PokemonDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "pokemon", url = "https://pokeapi.co/api/v2")
public interface PokemonClient {

    @GetMapping("/pokemon/{idPokemon}")
    PokemonDTO findPokemonByPokedexId(@PathVariable Long idPokemon);

}
