package com.minsait.clients;

import com.minsait.DTO.PokemonFoundDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservice-pokedex-pokemonfound", url = "localhost:8080/api/v1/pokemonfound")
public interface PokemonFoundClient {
    @GetMapping("/find-by-pokedex-id/{id}")
    ResponseEntity<?> findAllByPokedexId(@PathVariable Long id);
    @GetMapping("/find-by-pokemon-id/{id}")
    ResponseEntity<?> findByIdPokemon(@PathVariable Byte id);
    @PostMapping("/found-register")
    ResponseEntity<String> savePokemonFound(@RequestBody PokemonFoundDTO pokemonFound);
    @GetMapping("/{trainerId}")
    ResponseEntity<?> foundPokemonsByPokedexId(@PathVariable Long trainerId);

}
