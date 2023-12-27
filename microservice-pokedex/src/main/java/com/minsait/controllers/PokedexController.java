package com.minsait.controllers;

import com.minsait.models.Pokedex;
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

    @PostMapping("/create-pokedex")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCourse(@RequestBody Pokedex pokedex){
         pokedexService.savePokedex(pokedex);
    }

    @GetMapping
    public ResponseEntity<?> findAllCourses(){
        return ResponseEntity.ok((pokedexService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(pokedexService.findById(id));
    }

/*    @GetMapping("/search-by-trainer-id/{trainer}")
    public ResponseEntity<?> findByTeacher(@PathVariable Long trainer){
        return ResponseEntity.ok(pokedexService.findByTrainerId(trainer));
    }*/


}
