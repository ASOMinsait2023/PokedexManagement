package com.minsait.controllers;


import com.minsait.models.Pokedex;
import com.minsait.responses.PokemonByIdResponse;
import com.minsait.services.IPokedexService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/pokedex")
public class PokedexController {
    @Autowired
    private IPokedexService pokedexService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Pokedex save(@Valid @RequestBody Pokedex pokedex){
        Pokedex pokedexOptional = Optional.ofNullable(pokedexService.findById(pokedex.getId()))
                .orElseThrow(NoSuchElementException::new);
        return pokedexService.save(pokedexOptional);
    }

    @GetMapping
    @Transactional(readOnly = true)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findAllPokedex(){
        return ResponseEntity.ok((pokedexService.findAll()));
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(pokedexService.findById(id));
        }catch (NoSuchElementException e){
            throw new NoSuchElementException("Element not found");
        }

    }

    @GetMapping("/find-pokemon-by-pokedex-region/{pokedexId}")
    @Transactional(readOnly = true)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findPokemonByRegion(@PathVariable Long pokedexId) {

        Pokedex pokedexOptional = Optional.ofNullable(pokedexService.findById(pokedexId))
                .orElseThrow(NoSuchElementException::new);

        PokemonByIdResponse response = pokedexService.findPokemonByPokedexId(pokedexOptional.getId());
        Map<String, Object> jsonResponse = new LinkedHashMap<>();
        jsonResponse.put("id", response.getId());
        jsonResponse.put("region", response.getRegion());
        jsonResponse.put("pokemonsInRegion:", response.getPokemonsInRegion());
        return ResponseEntity.ok(jsonResponse);
    }

    @Transactional(readOnly = true)
    @GetMapping("/found-pokemons-by-pokedex-id/{pokedexId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> foundPokemonsByPokedexId(@PathVariable Long pokedexId) {
        Pokedex pokedexOptional = Optional.ofNullable(pokedexService.findById(pokedexId))
                .orElseThrow(NoSuchElementException::new);

        PokemonByIdResponse pokemonFound = pokedexService.foundPokemonsByPokedexId(pokedexOptional.getId());

        Map<String, Object> response = new HashMap<>();
        response.put("id", pokemonFound.getId());
        response.put("region", pokemonFound.getRegion());
        response.put("pokemonFoundList", pokemonFound.getPokemonFoundList());

        return ResponseEntity.ok(response);
    }
    @PatchMapping("/{id}/update-trainer-note")
    @ResponseStatus(HttpStatus.OK)
    public void updateTrainerNotes(@PathVariable Long id, @Valid @RequestBody Map<String, String> requestBody) {
        Pokedex pokedex = Optional.ofNullable(pokedexService.findById(id))
                .orElseThrow(NoSuchElementException::new);


        String trainerNotes = requestBody.get("trainerNotes");

        pokedex.setTrainerNotes(trainerNotes);

        pokedexService.save(pokedex);
    }
    @PatchMapping("/{id}/add-trainer-note")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addTrainerNote(@PathVariable Long id, @Valid @RequestBody String newNote) {
        Pokedex pokedex = Optional.ofNullable(pokedexService.findById(id))
                .orElseThrow(NoSuchElementException::new);

        String updatedNotes = pokedex.getTrainerNotes() + " --- " + newNote;

        pokedex.setTrainerNotes(updatedNotes);

        pokedexService.save(pokedex);

        return ResponseEntity.ok(pokedex);
    }

    @PatchMapping("/{id}/delete-all-trainer-notes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteTrainerNotes(@Valid @PathVariable Long id) {
        Pokedex pokedex = Optional.ofNullable(pokedexService.findById(id))
                .orElseThrow(NoSuchElementException::new);

        pokedex.setTrainerNotes("");

        pokedexService.save(pokedex);

        return ResponseEntity.ok(pokedex);
    }


}
