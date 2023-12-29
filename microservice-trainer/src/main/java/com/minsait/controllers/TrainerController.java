package com.minsait.controllers;

import com.minsait.clients.PokedexClient;
import com.minsait.responses.PokedexResponse;
import com.minsait.services.TrainerServiceImplements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/trainer")
public class TrainerController {
    @Autowired
    private TrainerServiceImplements trainerService;
    @Autowired
    private PokedexClient pokedexClient;

    @GetMapping("/hi")
    public String Hi(){
        return "Hi!";
    }

    @PatchMapping("/{id}/update-trainer-notes")
    public void updateTrainerNotes(@PathVariable Long id, @RequestBody Map<String, String> updatedNotes) {
        String trainerNotes = updatedNotes.get("trainerNotes");
       pokedexClient.updateTrainerNotes(id,trainerNotes);
    }
}
