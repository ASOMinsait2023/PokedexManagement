package com.minsait.microservicepokedex.controllers;

import com.minsait.controllers.PokedexController;
import com.minsait.models.Pokedex;
import com.minsait.models.Region;
import com.minsait.responses.PokemonByIdResponse;
import com.minsait.services.IPokedexService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PokemonControllerTest {

    @InjectMocks
    private PokedexController pokedexController;

    @Mock
    private IPokedexService pokedexService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSavePokedex() {
        Pokedex pokedex = new Pokedex();
        pokedex.setId(1L);

        when(pokedexService.findById(1L)).thenReturn(pokedex);
        when(pokedexService.save(pokedex)).thenReturn(pokedex);

        Pokedex result = pokedexController.save(pokedex);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);

        verify(pokedexService, times(1)).findById(1L);
        verify(pokedexService, times(1)).save(pokedex);
    }

    @Test
    public void testFindAllPokedex() {
        List<Pokedex> pokedexList = Arrays.asList(new Pokedex(), new Pokedex());

        when(pokedexService.findAll()).thenReturn(pokedexList);

        ResponseEntity<?> responseEntity = pokedexController.findAllPokedex();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(pokedexList);

        verify(pokedexService, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Pokedex pokedex = new Pokedex();
        pokedex.setId(id);

        when(pokedexService.findById(id)).thenReturn(pokedex);

        ResponseEntity<?> responseEntity = pokedexController.findById(id);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(pokedex);

        verify(pokedexService, times(1)).findById(id);
    }

    @Test
    public void testFindPokemonByRegion() {
        Long pokedexId = 1L;
        Pokedex pokedex = new Pokedex();
        pokedex.setId(pokedexId);
        PokemonByIdResponse pokemonResponse = new PokemonByIdResponse();
        pokemonResponse.setId(1L);
        pokemonResponse.setRegion(Region.Kanto);

        when(pokedexService.findById(pokedexId)).thenReturn(pokedex);
        when(pokedexService.findPokemonByPokedexId(pokedexId)).thenReturn(pokemonResponse);

        ResponseEntity<?> responseEntity = pokedexController.findPokemonByRegion(pokedexId);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isInstanceOf(Map.class);
        Map<String, Object> responseBody = (Map<String, Object>) responseEntity.getBody();
        assertThat(responseBody.get("id")).isEqualTo(1L);
        assertThat(responseBody.get("region")).isEqualTo(Region.Kanto);
        assertThat(responseBody.get("pokemonsInRegion")).isNull();

        verify(pokedexService, times(1)).findById(pokedexId);
        verify(pokedexService, times(1)).findPokemonByPokedexId(pokedexId);
    }

    @Test
    public void testFoundPokemonsByPokedexId() {
        Long pokedexId = 1L;
        Pokedex pokedex = new Pokedex();
        pokedex.setId(pokedexId);
        PokemonByIdResponse pokemonResponse = new PokemonByIdResponse();
        pokemonResponse.setId(1L);
        pokemonResponse.setRegion(Region.Kanto);

        when(pokedexService.findById(pokedexId)).thenReturn(pokedex);
        when(pokedexService.foundPokemonsByPokedexId(pokedexId)).thenReturn(pokemonResponse);

        ResponseEntity<?> responseEntity = pokedexController.foundPokemonsByPokedexId(pokedexId);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isInstanceOf(Map.class);
        Map<String, Object> responseBody = (Map<String, Object>) responseEntity.getBody();
        assertThat(responseBody.get("id")).isEqualTo(1L);
        assertThat(responseBody.get("region")).isEqualTo(Region.Kanto);
        assertThat(responseBody.get("pokemonFoundList")).isNull();

        verify(pokedexService, times(1)).findById(pokedexId);
        verify(pokedexService, times(1)).foundPokemonsByPokedexId(pokedexId);
    }

    @Test
    public void testUpdateTrainerNotes() {
        Long id = 1L;
        Pokedex pokedex = new Pokedex();
        pokedex.setId(id);
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("trainerNotes", "Updated trainer notes");

        when(pokedexService.findById(id)).thenReturn(pokedex);

        pokedexController.updateTrainerNotes(id, requestBody);

        assertThat(pokedex.getTrainerNotes()).isEqualTo("Updated trainer notes");

        verify(pokedexService, times(1)).findById(id);
        verify(pokedexService, times(1)).save(pokedex);
    }

    @Test
    public void testAddTrainerNote() {
        Long id = 1L;
        Pokedex pokedex = new Pokedex();
        pokedex.setId(id);
        String newNote = "New note";

        when(pokedexService.findById(id)).thenReturn(pokedex);

        ResponseEntity<?> responseEntity = pokedexController.addTrainerNote(id, newNote);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(pokedex);

        assertThat(pokedex.getTrainerNotes()).isEqualTo("New note");

        verify(pokedexService, times(1)).findById(id);
        verify(pokedexService, times(1)).save(pokedex);
    }

    @Test
    public void testDeleteTrainerNotes() {
        Long id = 1L;
        Pokedex pokedex = new Pokedex();
        pokedex.setId(id);
        pokedex.setTrainerNotes("Some notes");

        when(pokedexService.findById(id)).thenReturn(pokedex);

        ResponseEntity<?> responseEntity = pokedexController.deleteTrainerNotes(id);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(pokedex);

        assertThat(pokedex.getTrainerNotes()).isEqualTo("");

        verify(pokedexService, times(1)).findById(id);
        verify(pokedexService, times(1)).save(pokedex);
    }
}