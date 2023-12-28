package com.minsait.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PokemonDTO {
    private Long id;
    private String name;
    private List<TypeDTO> types;
    private int weight;

}
