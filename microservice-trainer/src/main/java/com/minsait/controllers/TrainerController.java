package com.minsait.controllers;

import com.minsait.clients.PokedexClient;
import com.minsait.models.Trainer;
import com.minsait.responses.PokedexResponse;
import com.minsait.services.ITrainerService;
import com.minsait.services.TrainerServiceImplements;
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
    public ResponseEntity<?> findAllStudent(){
        return ResponseEntity.ok((trainerService.findAll()));
    }

    @GetMapping("/{id}")

    public ResponseEntity<?> findById(@PathVariable Long id){
        return  ResponseEntity.ok(trainerService.findById(id));
    }

    @GetMapping("/search-by-id")
    public ResponseEntity<?> findByIdWithParam(@RequestParam Long id) {
        try {
            Trainer student = trainerService.findById(id);
            return ResponseEntity.ok(student);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/hi")
    public String Hi(){
        return "Hi!";
    }

    @PatchMapping("/{id}/update-trainer-notes")
    public void updateTrainerNotes(@PathVariable Long id, @RequestBody Map<String, String> updatedNotes) {

       trainerService.setTrainerNotes(id, updatedNotes);
    }
}
