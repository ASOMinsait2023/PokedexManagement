package com.minsait.controllers;


import com.minsait.models.Pokedex;
import com.minsait.responses.PokemonByIdResponse;
import com.minsait.services.IPokedexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pokedex")
public class PokedexController {
    @Autowired
    private IPokedexService pokedexService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Pokedex save(@RequestBody Pokedex pokedex){
        return pokedexService.save(pokedex);
    }

    @GetMapping
    public ResponseEntity<?> findAllPokedex(){
        return ResponseEntity.ok((pokedexService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(pokedexService.findById(id));
    }

    @GetMapping("/find-pokemon-by-pokedex-region/{pokedexid}")
    public ResponseEntity<?> findPokemonByRegion(@PathVariable Long pokedexid) {
        PokemonByIdResponse response = pokedexService.findPokemonByPokedexId(pokedexid);
        return ResponseEntity.ok(response);
    }

}
