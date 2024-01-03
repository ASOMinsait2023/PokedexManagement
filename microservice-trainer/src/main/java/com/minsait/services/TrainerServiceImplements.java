package com.minsait.services;

import com.minsait.DTO.PokedexDTO;
import com.minsait.DTO.PokemonFoundDTO;
import com.minsait.clients.PokedexClient;
import com.minsait.clients.PokemonFoundClient;
import com.minsait.models.Trainer;
import com.minsait.repositories.ITrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class TrainerServiceImplements implements ITrainerService{
    @Autowired
    private ITrainerRepository trainerRepository;
    @Autowired
    private PokedexClient pokedexClient;
    @Autowired
    PokemonFoundClient pokemonFoundClient;

    @Override
    @Transactional(readOnly = true)
    public List<Trainer> findAll() {
        return trainerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Trainer findById(Long id) {
        return trainerRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Trainer trainer) {
        trainerRepository.save(trainer);
    }

    @Override
    public void setTrainerNotes(Long id, Map<String, String> updatedNotes) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow();
        pokedexClient.updateTrainerNotes(trainer.getIdPokedex(), updatedNotes);
    }

    @Override
    public void addTrainerNote(Long id, String newNotes) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow();

        pokedexClient.addTrainerNote(trainer.getIdPokedex(), newNotes);
    }

    @Override
    public void deleteTrainerNotes(Long trainerId) {
        Trainer trainer = trainerRepository.findById(trainerId).orElseThrow();
        pokedexClient.deleteTrainerNotes(trainer.getIdPokedex());
    }


    @Override
    public ResponseEntity<?> findByIdPokemon(Byte id) {
        return pokemonFoundClient.findByIdPokemon(id);
    }

    @Override
    public ResponseEntity<?> foundPokemonsByTrainerPokedexId(Long id) {
        Trainer trainer = trainerRepository.findById(id).orElseThrow();

        return pokedexClient.foundPokemonsByPokedexId(trainer.getIdPokedex());
    }

    @Override
    public PokemonFoundDTO savePokemonFound(PokemonFoundDTO pokemonFound) {
        pokemonFoundClient.savePokemonFound(pokemonFound);
        return pokemonFound;
    }

    @Override
    public PokedexDTO findPokedexById(Long id){

        Trainer trainer= trainerRepository.findById(id).orElseThrow();
        System.out.println(trainer.getIdPokedex());

        return ResponseEntity.ok(pokedexClient.findPokedexById(trainer.getIdPokedex())).getBody();

    }

}
