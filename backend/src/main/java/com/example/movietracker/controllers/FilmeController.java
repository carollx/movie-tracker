package com.example.movietracker.controllers;

import com.example.movietracker.entities.Filme;
import com.example.movietracker.services.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @PostMapping
    public ResponseEntity<Filme> cadastrarFilme(@RequestBody Filme filme) {
        Filme filmeCriado = filmeService.salvarFilme(filme);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmeCriado);
    }

    @GetMapping
    public ResponseEntity<List<Filme>> listarFilmes() {
        List<Filme> filmes = filmeService.listarFilmes();
        return ResponseEntity.ok(filmes);
    }

    @PutMapping("/{id}/assistido")
    public ResponseEntity<Filme> marcarComoAssistido(@PathVariable Long id) {
        Filme filme = filmeService.marcarComoAssistido(id);
        if (filme == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(filme);
    }
}
