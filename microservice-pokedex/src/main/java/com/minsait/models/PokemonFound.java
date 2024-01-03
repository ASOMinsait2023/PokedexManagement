package com.minsait.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "pokemon")
@AllArgsConstructor
@NoArgsConstructor
public class PokemonFound {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_pokemon")
    private Long idPokemon;
    @ManyToOne
    @JoinColumn(name = "pokedex_id")
    @JsonBackReference
    private Pokedex pokedex;

}
