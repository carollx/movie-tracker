package com.example.movietracker.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoDTO {
    private Long filmeId;
    private Integer nota;
    private String comentario;
}
