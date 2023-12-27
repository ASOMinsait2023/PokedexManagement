package com.minsait.clients;

import com.minsait.DTO.PokemonDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(name = "pokemon", url = "https://pokeapi.co/api/v2/pokemon")
public interface PokemonClient {

    @GetMapping("/{idPokemon}")
    List<PokemonDTO> findByPokemonId(@PathVariable Long idPokemon);

}
