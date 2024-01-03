package com.minsait.controllers;


import com.minsait.models.Pokedex;
import com.minsait.responses.PokemonByIdResponse;
import com.minsait.services.IPokedexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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

    @GetMapping("/find-pokemon-by-pokedex-region/{pokedexId}")
    public ResponseEntity<?> findPokemonByRegion(@PathVariable Long pokedexId) {
        PokemonByIdResponse response = pokedexService.findPokemonByPokedexId(pokedexId);
        Map<String, Object> jsonResponse = new LinkedHashMap<>();
        jsonResponse.put("id", response.getId());
        jsonResponse.put("region", response.getRegion());
        jsonResponse.put("pokemonsInRegion:", response.getPokemonsInRegion());
        return ResponseEntity.ok(jsonResponse);
    }

    @GetMapping("/found-pokemons-by-pokedex-id/{pokedexId}")
    public ResponseEntity<?> foundPokemonsByPokedexId(@PathVariable Long pokedexId) {
        PokemonByIdResponse pokemonFound = pokedexService.foundPokemonsByPokedexId(pokedexId);

        Map<String, Object> response = new HashMap<>();
        response.put("id", pokemonFound.getId());
        response.put("region", pokemonFound.getRegion());
        response.put("pokemonFoundList", pokemonFound.getPokemonFoundList());

        return ResponseEntity.ok(response);
    }
    @PatchMapping("/{id}/update-trainer-note")
    public void updateTrainerNotes(@PathVariable Long id, @RequestBody Map<String, String> requestBody) {
        Pokedex pokedex = pokedexService.findById(id);

        String trainerNotes = requestBody.get("trainerNotes");

        pokedex.setTrainerNotes(trainerNotes);

        pokedexService.save(pokedex);
    }
    @PatchMapping("/{id}/add-trainer-note")
    public ResponseEntity<?> addTrainerNote(@PathVariable Long id, @RequestBody String newNote) {
        Pokedex pokedex = pokedexService.findById(id);

        String updatedNotes = pokedex.getTrainerNotes() + " --- " + newNote;

        pokedex.setTrainerNotes(updatedNotes);

        pokedexService.save(pokedex);

        return ResponseEntity.ok(pokedex);
    }

    @PatchMapping("/{id}/delete-all-trainer-notes")
    public ResponseEntity<?> deleteTrainerNotes(@PathVariable Long id) {
        Pokedex pokedex = pokedexService.findById(id);

        pokedex.setTrainerNotes("");

        pokedexService.save(pokedex);

        return ResponseEntity.ok(pokedex);
    }


}
