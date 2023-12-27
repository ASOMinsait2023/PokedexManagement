package com.minsait.services;

import com.minsait.models.Pokedex;
import com.minsait.repositories.IPokedexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PokedexServiceImplements implements IPokedexService{
    @Autowired
    private IPokedexRepository pokedexRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Pokedex> findAll() {
        return (List<Pokedex>) pokedexRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Pokedex findById(Long id) {
        return pokedexRepository.findById(id).orElseThrow();
    }

    @Override
    public void savePokedex(Pokedex pokedex) {
        pokedexRepository.save(pokedex);
    }

/*    @Override
    @Transactional(readOnly = true)
    public List<Pokedex> findByTrainerId(Long idTrainer) {
        return pokedexRepository.findAllByTrainerId(idTrainer);
    }*/
}
