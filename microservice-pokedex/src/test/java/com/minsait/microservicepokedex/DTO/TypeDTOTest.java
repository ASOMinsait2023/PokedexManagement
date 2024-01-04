package com.minsait.microservicepokedex.DTO;

import com.minsait.DTO.TypeDTO;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TypeDTOTest {

    @Test
    public void testTypeDTOStructure() {

        TypeDTO.Type type = new TypeDTO.Type("Electric");
        TypeDTO typeDTO = new TypeDTO(1, type);

        assertThat(typeDTO.getSlot()).isEqualTo(1);
        assertThat(typeDTO.getType().getName()).isEqualTo("Electric");
    }
}
