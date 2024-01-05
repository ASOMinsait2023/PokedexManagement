package com.minsait.microservicepokedex.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minsait.clients.PokemonClient;
import com.minsait.controllers.PokedexController;
import com.minsait.models.Pokedex;
import com.minsait.models.PokemonFound;
import com.minsait.models.Region;
import com.minsait.responses.PokemonByIdResponse;
import com.minsait.services.IPokedexService;
import com.minsait.services.IPokemonFoundService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.in;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PokedexController.class)
public class PokedexControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private IPokedexService service;
    @MockBean
    private PokemonClient pokemonClient;
    @MockBean
    private IPokemonFoundService pService;
    ObjectMapper mapper;
    @BeforeEach
    void setUp(){
        mapper= new ObjectMapper();
        when(pokemonClient.findPokemonByPokedexId(any())).thenReturn(Datos.createPokemonDTO().get());
    }




    @Test
    void testFindAll() throws Exception {
        when(service.findAll())
                .thenReturn(List.of(Datos.crearPokedex1().get(),Datos.crearPokedex2().get()));


        mvc.perform(get("/api/v1/pokedex").contentType(MediaType.APPLICATION_JSON))
                //then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("4"))
                .andExpect(jsonPath("$[1].id").value("1"))
                .andExpect(jsonPath("$[0].region").value("Kanto"))
        ;
    }

    @Test
    void save() throws Exception{
               Pokedex pokedex = new Pokedex(
                null,
                Region.Kanto,
                "Desc",
                "Notes",
                Datos.getPokemonFound());

        when(service.save(any(Pokedex.class))).then(invocationOnMock -> {
            Pokedex pokedex1=invocationOnMock.getArgument(0);
            pokedex1.setDescription("Salu2");
            pokedex1.setId(7L);
            return  pokedex1;
        });

        mvc.perform(post("/api/v1/pokedex/create").contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(pokedex)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(7)))
                .andExpect(jsonPath("$.region").value("Kanto"))
                .andExpect(jsonPath("$.description").value("Salu2"))
                .andExpect(jsonPath("$.trainerNotes").value("Notes"));
    }

    @Test
    void findById() throws Exception {
        when(service.findById(1L)).thenReturn(Datos.crearPokedex2().get());
        mvc.perform(get("/api/v1/pokedex/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.region").value("Kanto"));
    }

    @Test
    void findByIdIfDoesNotExist() throws Exception {
        when(service.findById(1L)).thenThrow(NoSuchElementException.class);
        mvc.perform(get("/api/v1/pokedex/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void testFindPokemonByRegion() throws Exception {
        Pokedex mockExpected = Datos.crearPokedex2().get();
        when(service.findById(1L)).thenReturn(Datos.crearPokedex2().get());
        when(service.findPokemonByPokedexId(Datos.crearPokedex2().get().getId())).thenReturn(Datos.CreatePokemonByIdResponse().get());

        mvc.perform(get("/api/v1/pokedex/find-pokemon-by-pokedex-region/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockExpected.getId()))
                .andExpect(jsonPath("$.region").value(mockExpected.getRegion().toString()))
                .andExpect(jsonPath("$.pokemonsInRegion.[0]").value(Datos.createPokemonDTOList().get(0)));

        verify(service, times(1)).findPokemonByPokedexId(1L);
    }


    @Test
    public void foundPokemonsByPokedexId() throws Exception{
        Pokedex mockPokedex = Datos.crearPokedex2().get();
        PokemonByIdResponse mockResponse = Datos.CreatePokemonByIdResponse().get();
        when(service.findById(mockPokedex.getId())).thenReturn(mockPokedex);
        when(pService.findByIdPokemon(any())).thenReturn(Datos.getPokemonFound());
        when(service.foundPokemonsByPokedexId(mockPokedex.getId()))
                .thenReturn(mockResponse);


        mvc.perform(get("/api/v1/pokedex/found-pokemons-by-pokedex-id/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(mockPokedex.getId()))
                .andExpect(jsonPath("$.region").value(mockPokedex.getRegion().toString()))
                .andExpect(jsonPath("$.pokemonFoundList.[0]").value(Datos.createPokemonDTO().get()));
    }


    @Test
    public void updateTrainerNotes() throws Exception{
        String body = "{\"trainerNotes\":\"sac00\"}";
        Pokedex pokedex =Datos.crearPokedex1().get();
        when(service.findById(1L)).thenReturn(pokedex);

        when(service.save(any(Pokedex.class))).thenReturn(pokedex);

        mvc.perform(patch("/api/v1/pokedex/1/update-trainer-note")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk());
        assertEquals("sac00", pokedex.getTrainerNotes());
    }

    @Test
    public void addTrainerNotes() throws Exception{
        String body = "When a woman loves a man";
        Pokedex pokedex =Datos.crearPokedex1().get();
        pokedex.setTrainerNotes("hi dude!");
        String notesBefore = pokedex.getTrainerNotes();
        when(service.findById(1L)).thenReturn(pokedex);

        when(service.save(any(Pokedex.class))).thenReturn(pokedex);

        mvc.perform(patch("/api/v1/pokedex/1/add-trainer-note")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content(body))
                .andExpect(status().isOk());
        assertEquals(notesBefore+" --- "+body, pokedex.getTrainerNotes());
    }

    @Test
    public void deleteTrainerNotes()throws Exception{
        Pokedex pokedex =Datos.crearPokedex1().get();
        when(service.findById(1L)).thenReturn(pokedex);
        when(service.save(any(Pokedex.class))).thenReturn(pokedex);

        mvc.perform(patch("/api/v1/pokedex/1/delete-all-trainer-notes"))
                .andExpect(status().isNoContent());
        assertEquals("", pokedex.getTrainerNotes());

    }


}