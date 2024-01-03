package com.minsait.services;

import com.minsait.DTO.PokedexDTO;
import com.minsait.DTO.PokemonFoundDTO;
import com.minsait.models.Trainer;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ITrainerService {
    List<Trainer> findAll();
    Trainer findById(Long id);
    void save(Trainer trainer);

    PokedexDTO findPokedexById(Long trainerId);
    void  setTrainerNotes(Long id, Map<String, String> requestBody);

    void addTrainerNote(Long id,  String newNote);

    void deleteTrainerNotes(Long id);

    ResponseEntity<?>findByIdPokemon(Byte id);

    ResponseEntity<?>foundPokemonsByTrainerPokedexId(Long id);
    PokemonFoundDTO savePokemonFound(PokemonFoundDTO pokemonFound);


}
