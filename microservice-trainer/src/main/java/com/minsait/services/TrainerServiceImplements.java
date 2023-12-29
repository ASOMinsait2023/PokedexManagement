package com.minsait.services;

import com.minsait.DTO.PokedexDTO;
import com.minsait.DTO.PokemonDTO;
import com.minsait.clients.PokedexClient;
import com.minsait.models.Trainer;
import com.minsait.repositories.ITrainerRepository;
import com.minsait.responses.PokedexResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class TrainerServiceImplements implements ITrainerService{
    @Autowired
    private ITrainerRepository trainerRepository;
    @Autowired
    private PokedexClient pokedexClient;

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
    public Trainer save(Trainer trainer) {

        trainerRepository.save(trainer);
        return trainer;
    }


    @Override
    public void setTrainerNotes(Long id, Map<String, String> updatedNotes) {
        Long pokedexId = trainerRepository.findById(id).get().getIdPokedex();
        pokedexClient.updateTrainerNotes(pokedexId, updatedNotes);
    }
}
