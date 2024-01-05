package com.minsait.microservicepokedex.controllers;

import com.minsait.DTO.PokemonDTO;
import com.minsait.DTO.TypeDTO;
import com.minsait.models.Pokedex;
import com.minsait.models.PokemonFound;
import com.minsait.models.Region;
import com.minsait.responses.PokemonByIdResponse;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

public class Datos {
    public static Optional<Pokedex> crearPokedex1(){
        List<PokemonFound> pokemonFoundList = List.of( new PokemonFound(2L,2L, new Pokedex()));

        return Optional.of( new Pokedex(4L, Region.Kanto,"Descripción de Kanto","",pokemonFoundList ));
    }
    public static Optional<Pokedex> crearPokedex2(){

        List<PokemonFound> pokemonFoundList = List.of( new PokemonFound(1L,1L, new Pokedex()),new PokemonFound(1L,2L, new Pokedex()));

        return Optional.of( new Pokedex(1L, Region.Kanto,"Desc","TN", pokemonFoundList ));
    }

    public static Pokedex getPokedex(){
        return new Pokedex(1L, Region.Kanto,"Desc","TN",new ArrayList<PokemonFound>());
    }
    public static Optional<PokemonDTO> createPokemonDTO(){
        List<TypeDTO> typeDTOList = new ArrayList<>();
        typeDTOList.add(new TypeDTO(1, new TypeDTO.Type("Electric")));

        return Optional.of(new PokemonDTO(1L,"Pikachu", typeDTOList,23 ));
    }

    public static List<PokemonDTO> createPokemonDTOList(){
        List<TypeDTO> typeDTOList = new ArrayList<>();
        typeDTOList.add(new TypeDTO(1, new TypeDTO.Type("Electric")));
        return List.of(new PokemonDTO(1L,"Pikachu", typeDTOList,23 ),new PokemonDTO(2L,"Poni", typeDTOList,23 ));
    }

    public static List<PokemonFound> getPokemonFound(){
        List<PokemonFound> pokemonFoundList = new ArrayList<>();
        Pokedex pokedex = crearPokedex2().get();

        PokemonFound pokemonFound1 = new PokemonFound(1L,1L,pokedex);
        PokemonFound pokemonFound2 = new PokemonFound(2L,2L,pokedex);

        pokemonFoundList.add(pokemonFound1);
        pokemonFoundList.add(pokemonFound2);
        return pokemonFoundList;
    }

    public static Optional<PokemonByIdResponse> CreatePokemonByIdResponse(){
        List<PokemonDTO> pokemonFoundList = new ArrayList<>();
        List<TypeDTO> typeDTOList = new ArrayList<>();
        typeDTOList.add(new TypeDTO(1, new TypeDTO.Type("Electric")));

        pokemonFoundList.add(new PokemonDTO(1L,"Pikachu", typeDTOList,23 ));
        PokemonByIdResponse response = new PokemonByIdResponse(1L,Region.Kanto, pokemonFoundList,pokemonFoundList);
        return  Optional.of(response);
    }


}

