package com.minsait.controllers;


import com.minsait.DTO.PokedexDTO;
import com.minsait.DTO.PokemonFoundDTO;
import com.minsait.models.Trainer;
import com.minsait.services.ITrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/trainer")
public class TrainerController {
    @Autowired
    private ITrainerService trainerService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Trainer trainer){
        trainerService.save(trainer);
    }

    @GetMapping
    public ResponseEntity<?> findAllTrainers(){
        return ResponseEntity.ok(trainerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return  ResponseEntity.ok(trainerService.findById(id));
    }

    @PatchMapping("/{id}/update-trainer-notes")
    public void updateTrainerNotes(@PathVariable Long id, @RequestBody Map<String, String> updatedNotes) {
        trainerService.setTrainerNotes(id, updatedNotes);
    }

    @PatchMapping("/{id}/add-trainer-notes")
    public void addTrainerNotes(@PathVariable Long id, @RequestBody String newNotes) {

        trainerService.addTrainerNote(id, newNotes);
    }
    @PatchMapping("/{id}/delete-all-trainer-notes")
    public void deleteTrainerNotes(@PathVariable Long id) {
        trainerService.deleteTrainerNotes(id);
    }

    @GetMapping("/hi")
    public String Hi(){
        return "Hi!";
    }


    @GetMapping("/find-by-pokemon-id/{id}")
    ResponseEntity<?> findByIdPokemon(@PathVariable Byte id){

        return ResponseEntity.ok(trainerService.findByIdPokemon(id).getBody());
    }

    @PostMapping("/found-register")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<String> savePokemonFound(@RequestBody PokemonFoundDTO pokemonFound){

        trainerService.savePokemonFound(pokemonFound);
        return ResponseEntity.ok("New Pokemon registered in your Pokedex");
    }

    @GetMapping("/found-pokemons-by-trainer-pokedex-id/{trainerId}")
    public ResponseEntity<?> foundPokemonsByTrainerPokedexId(@PathVariable Long trainerId) {

        return ResponseEntity.ok(trainerService.foundPokemonsByTrainerPokedexId(trainerId).getBody());
    }


}
