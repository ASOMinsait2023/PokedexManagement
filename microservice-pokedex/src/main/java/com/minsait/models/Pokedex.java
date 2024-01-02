package com.minsait.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Table(name = "pokedex")
@AllArgsConstructor
@NoArgsConstructor
public class Pokedex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pokedex")
    private Long id;
    @Enumerated(EnumType.STRING)
    private Region region;
    @Column(name = "poke_description")
    private String description;
    @Column(name = "trainer_notes")
    private String trainerNotes;

    @OneToMany(mappedBy = "pokedex")
    private List<Pokemon> pokemons;
}
