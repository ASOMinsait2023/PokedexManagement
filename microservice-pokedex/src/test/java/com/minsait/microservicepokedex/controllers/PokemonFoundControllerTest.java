package com.minsait.microservicepokedex.controllers;

import com.minsait.controllers.PokemonFoundController;
import com.minsait.models.Pokedex;
import com.minsait.models.PokemonFound;
import com.minsait.services.IPokedexService;
import com.minsait.services.IPokemonFoundService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PokemonFoundControllerTest {

    @InjectMocks
    private PokemonFoundController controller;

    @Mock
    private IPokemonFoundService pokemonService;

    @Mock
    private IPokedexService pokedexService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSavePokemonFound() {
        PokemonFound pokemonFound = new PokemonFound();
        pokemonFound.setIdPokemon(1L);

        Pokedex pokedex = new Pokedex();
        pokedex.setId(1L);

        pokemonFound.setPokedex(pokedex);

        when(pokedexService.findById(pokemonFound.getPokedex().getId())).thenReturn(pokedex);

        ResponseEntity<String> responseEntity = controller.save(pokemonFound);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isEqualTo("New Pokemon registered in your Pokedex");
        verify(pokemonService, times(1)).save(pokemonFound);
    }


    @Test
    public void testSavePokemonFoundPokedexNotFound() {
        PokemonFound pokemonFound = new PokemonFound();
        pokemonFound.setIdPokemon(1L);
        pokemonFound.setPokedex(new Pokedex());
        pokemonFound.getPokedex().setId(1L);

        when(pokedexService.findById(pokemonFound.getPokedex().getId())).thenReturn(null);

        ResponseEntity<String> responseEntity = controller.save(pokemonFound);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(responseEntity.getBody()).isEqualTo("Pokedex with given ID not found.");
        verify(pokemonService, never()).save(any());
    }

    @Test
    public void testFindAllByPokedexId() {
        Long pokedexId = 1L;
        List<PokemonFound> pokemonFoundList = new ArrayList<>();

        when(pokemonService.findAllByPokedexId(pokedexId)).thenReturn(pokemonFoundList);

        ResponseEntity<?> responseEntity = controller.findAllByPokedexId(pokedexId);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(pokemonFoundList);
    }

    @Test
    public void testFindByIdPokemon() {
        Long id = 1L;
        List<PokemonFound> pokemonFoundList = new ArrayList<>();

        when(pokemonService.findByIdPokemon(id)).thenReturn(pokemonFoundList);

        ResponseEntity<?> responseEntity = controller.findByIdPokemon(id);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
