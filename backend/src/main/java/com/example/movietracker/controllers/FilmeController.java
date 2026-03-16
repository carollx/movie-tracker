package com.example.movietracker.controllers;

import com.example.movietracker.entities.Filme;
import com.example.movietracker.services.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @PostMapping
    public ResponseEntity<Filme> cadastrarFilme(@RequestBody Filme filme) {
        Filme filmesCriado = filmeService.salvarFilme(filme);
        return ResponseEntity.status(HttpStatus.CREATED).body(filmesCriado);
    }

    @GetMapping
    public ResponseEntity<List<Filme>> listarTodosFilmes() {
        List<Filme> filmes = filmeService.listarTodosFilmes();
        return ResponseEntity.ok(filmes);
    }

    // Rotas específicas DEVEM vir ANTES da rota genérica /{id}
    @GetMapping("/assistidos")
    public ResponseEntity<List<Filme>> listarFilmesAssistidos() {
        List<Filme> filmes = filmeService.listarFilmesAssistidos();
        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/nao-assistidos")
    public ResponseEntity<List<Filme>> listarFilmesNaoAssistidos() {
        List<Filme> filmes = filmeService.listarFilmesNaoAssistidos();
        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<Filme>> listarFilmesPorGenero(@PathVariable String genero) {
        List<Filme> filmes = filmeService.listarFilmesPorGenero(genero);
        return ResponseEntity.ok(filmes);
    }

    // Rota genérica DEVE vir POR ÚLTIMO
    @GetMapping("/{id}")
    public ResponseEntity<Filme> buscarFilmePorId(@PathVariable Long id) {
        Optional<Filme> filme = filmeService.buscarFilmePorId(id);
        return filme.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/assistido")
    public ResponseEntity<Filme> marcarComoAssistido(@PathVariable Long id) {
        Filme filme = filmeService.marcarComoAssistido(id);
        if (filme != null) {
            return ResponseEntity.ok(filme);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filme> atualizarFilme(@PathVariable Long id, @RequestBody Filme filme) {
        Filme filmeAtualizado = filmeService.atualizarFilme(id, filme);
        if (filmeAtualizado != null) {
            return ResponseEntity.ok(filmeAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFilme(@PathVariable Long id) {
        filmeService.deletarFilme(id);
        return ResponseEntity.noContent().build();
    }
}
