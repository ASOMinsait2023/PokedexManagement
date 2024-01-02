package com.minsait.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@Entity
@Table(name = "pokemon")
@AllArgsConstructor
@NoArgsConstructor
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_pokemon")
    private Byte idPokemon;
    private Boolean found;
    @ManyToOne
    @JoinColumn(name = "pokedex_id")
    private Pokedex pokedex;
}
