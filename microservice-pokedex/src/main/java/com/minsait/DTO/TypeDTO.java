package com.minsait.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeDTO {
    private int slot;
    private Type type;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Type {
        private String name;
    }
}