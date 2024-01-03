package com.minsait.controllers;


import com.minsait.DTO.PokedexDTO;
import com.minsait.DTO.PokemonFoundDTO;
import com.minsait.exceptions.DuplicatedRecordException;
import com.minsait.models.Trainer;
import com.minsait.services.ITrainerService;
import feign.FeignException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.ParameterOutOfBoundsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/trainer")
public class TrainerController {
    @Autowired
    private ITrainerService trainerService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody Trainer trainer){
        if (trainerService.findById(trainer.getId())!=null) trainerService.save(trainer);
        else throw new DuplicatedRecordException("This Trainer already exists, cannot be recreated");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = true)
    public ResponseEntity<?> findAllTrainers(){
        return ResponseEntity.ok(trainerService.findAll());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = true)
    public ResponseEntity<?> findById(@PathVariable Long id){

        Trainer trainer = Optional.ofNullable(trainerService.findById(id))
                .orElse(null);

        if (trainer != null) {
            return ResponseEntity.ok(trainer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}/update-trainer-notes")
    public void updateTrainerNotes(@PathVariable Long id, @Valid @RequestBody Map<String, String> updatedNotes) {
        Trainer trainer = Optional.ofNullable(trainerService.findById(id))
                .orElseThrow(NoSuchElementException::new);
        trainerService.setTrainerNotes(trainer.getId(), updatedNotes);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}/add-trainer-notes")
    public void addTrainerNotes(@PathVariable Long id,@Valid @RequestBody String newNotes) {
        Trainer trainer = Optional.ofNullable(trainerService.findById(id))
                .orElseThrow(NoSuchElementException::new);
        trainerService.addTrainerNote(trainer.getId(), newNotes);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}/delete-all-trainer-notes")
    public void deleteTrainerNotes(@PathVariable Long id) {
        Trainer trainer = Optional.ofNullable(trainerService.findById(id))
                .orElseThrow(NoSuchElementException::new);
        trainerService.deleteTrainerNotes(trainer.getId());
    }


    @GetMapping("/find-by-pokemon-id/{id}")
    ResponseEntity<?> findByIdPokemon(@PathVariable Byte id){
        if(trainerService.findByIdPokemon(id).hasBody())
        return ResponseEntity.ok(trainerService.findByIdPokemon(id).getBody());
        else throw new NoSuchElementException("Invalid id");
    }


    @PostMapping("/found-register")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<String> savePokemonFound(@RequestBody PokemonFoundDTO pokemonFound){
        if (pokemonFound.getIdPokemon() < 1 || pokemonFound.getIdPokemon() > 10) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid Pokemon ID. It must be between 1 and 10.");
        }

        try {
            trainerService.savePokemonFound(pokemonFound);
            return ResponseEntity.ok("New Pokemon registered in your Pokedex :)");
        }catch (ParameterOutOfBoundsException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid input data. Check your request body.");
        }catch (NoSuchElementException | FeignException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/found-pokemons-by-trainer-pokedex-id/{trainerId}")
    public ResponseEntity<?> foundPokemonsByTrainerPokedexId(@PathVariable Long trainerId) {

            ResponseEntity<?> response =trainerService.foundPokemonsByTrainerPokedexId(trainerId);

            if (response.getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.ok(response.getBody());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
    }


}
