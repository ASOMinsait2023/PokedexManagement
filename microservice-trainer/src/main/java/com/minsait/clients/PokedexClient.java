package com.minsait.clients;

import com.minsait.DTO.PokedexDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "microservice-pokedex", url = "localhost:7090/api/v1/pokedex")
public interface PokedexClient {
    @GetMapping("/{id}")
    PokedexDTO findById(@PathVariable Long id);

    @RequestMapping(value = "/{id}/add-trainer-note", method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> addTrainerNote(@PathVariable Long id, @RequestBody String newNote);
    @RequestMapping(value = "/{id}/update-trainer-note", method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void updateTrainerNotes(@PathVariable Long id, @RequestBody Map<String, String> requestBody);

    @RequestMapping(value = "/{id}/delete-trainer-note", method = RequestMethod.PATCH)
    ResponseEntity<?> deleteTrainerNotes(@PathVariable Long id);

}
