package com.minsait.clients;

import com.minsait.DTO.PokedexDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservice-pokedex", url = "localhost:7090/api/v1/pokedex")
public interface PokedexClient {
    @GetMapping("/{id}")
    PokedexDTO findById(@PathVariable Long id);

    @PatchMapping("/{id}/add-trainer-note")
    ResponseEntity<?> addTrainerNote(@PathVariable Long id, @RequestBody String newNote);
    @PatchMapping("/{id}/update-trainer-note")
    void updateTrainerNotes(@PathVariable Long id, @RequestBody String updatedNotes);

    @PatchMapping("/{id}/delete-trainer-notes")
    ResponseEntity<?> deleteTrainerNotes(@PathVariable Long id);

}
