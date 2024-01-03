package com.minsait.services;

import com.minsait.DTO.PokemonDTO;
import com.minsait.clients.PokemonClient;
import com.minsait.models.Pokedex;
import com.minsait.models.PokemonFound;
import com.minsait.models.Region;
import com.minsait.repositories.IPokedexRepository;
import com.minsait.repositories.IPokemonFoundRepository;
import com.minsait.responses.PokemonByIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokedexServiceImplements implements IPokedexService{
    @Autowired
    private IPokedexRepository pokedexRepository;

    @Autowired
    private PokemonClient pokemonClient;
    @Override
    @Transactional(readOnly = true)
    public List<Pokedex> findAll() {
        return pokedexRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Pokedex findById(Long id) {
        return pokedexRepository.findById(id).orElseThrow();
    }

    @Override
    public Pokedex save(Pokedex pokedex) {
        pokedexRepository.save(pokedex);
        return pokedex;
    }

    @Override
    @Transactional(readOnly = true)
    public PokemonByIdResponse findPokemonByPokedexId(Long id) {
        Pokedex pokedex = pokedexRepository.findById(id).orElseThrow();
        List<PokemonDTO> pokemonDTOList = new ArrayList<>();

        int startId = getStartIdForRegion(pokedex.getRegion());

        for (int i = 0; i < 10; i++) {
            PokemonDTO dto = pokemonClient.findPokemonByPokedexId((long) (startId + i));
            if (dto != null) {
                pokemonDTOList.add(dto);
            }
        }

        return PokemonByIdResponse.builder()
                .id(pokedex.getId())
                .region(pokedex.getRegion())
                .pokemonsInRegion(pokemonDTOList)
                .build();
    }

    @Transactional(readOnly = true)
    public PokemonByIdResponse foundPokemonsByPokedexId(Long id) {
        Pokedex pokedex = pokedexRepository.findById(id).orElseThrow();
        List<PokemonDTO> pokemonDTOList = new ArrayList<>();

        int startId = getStartIdForRegion(pokedex.getRegion());

        List<Long> foundPokemonIds = pokedex.getPokemonFound()
                .stream()
                .map(PokemonFound::getIdPokemon)
                .toList();

        for (Long idFound : foundPokemonIds) {
            PokemonDTO dto = pokemonClient.findPokemonByPokedexId((long) (startId-1 + idFound));
            if (dto != null) {
                pokemonDTOList.add(dto);
            }
        }

        return PokemonByIdResponse.builder()
                .id(pokedex.getId())
                .region(pokedex.getRegion())
                .pokemonFoundList(pokemonDTOList)
                .build();
    }

    private int getStartIdForRegion(Region region) {
        return switch (region) {
            case Kanto -> 1;
            case Johto -> 152;
            case Hoenn -> 252;
            default -> 1;
        };
    }
}
