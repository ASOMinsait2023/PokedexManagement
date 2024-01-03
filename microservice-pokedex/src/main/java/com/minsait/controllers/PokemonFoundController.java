package com.minsait.controllers;

import com.minsait.models.Pokedex;
import com.minsait.models.PokemonFound;
import com.minsait.services.IPokedexService;
import com.minsait.services.IPokemonFoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/pokemonfound")
public class PokemonFoundController {
    @Autowired
    IPokemonFoundService pokemonService;
    @Autowired
    IPokedexService pokedexService;
    @PostMapping("/found-register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> save(@RequestBody PokemonFound pokemonFound){

        PokemonFound pokemon = new PokemonFound();

        pokemon.setIdPokemon(pokemonFound.getIdPokemon());
        pokemon.setPokedex(pokedexService.findById(pokemonFound.getPokedex().getId()));

        pokemonService.save(pokemon);
        return ResponseEntity.ok("New Pokemon registered in your Pokedex");
    }

    @GetMapping("/find-by-pokedex-id/{id}")
    public ResponseEntity<?> findAllByPokedexId(@PathVariable Long id){

        return ResponseEntity.ok(pokemonService.findAllByPokedexId(id));
    }

    @GetMapping("/find-by-pokemon-id/{id}")
    public ResponseEntity<?> findByIdPokemon(@PathVariable Long id){
       List<PokemonFound> pokemonFoundList =  pokemonService.findByIdPokemon(id);
        List<Map<String, Object>> pokemonDetails = new ArrayList<>();

        for (PokemonFound pokemonFound : pokemonFoundList) {
            Map<String, Object> pokemonInfo = new HashMap<>();
            pokemonInfo.put("idGlobal", pokemonFound.getId());
            pokemonInfo.put("idPokemon", pokemonFound.getIdPokemon());
            pokemonInfo.put("pokedexId", pokemonFound.getPokedex() != null ? pokemonFound.getPokedex().getId() : null);
            pokemonDetails.add(pokemonInfo);
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("body", pokemonDetails);
        return new ResponseEntity<>(response, HttpStatus.OK);


    }
}
