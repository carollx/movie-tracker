package com.example.movietracker.repositories;

import com.example.movietracker.entities.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
    List<Filme> findByAssistido(Boolean assistido);
    List<Filme> findByGenero(String genero);
}
