package com.example.movietracker.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "filmes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private Integer ano;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false)
    private Boolean assistido = false;
}
