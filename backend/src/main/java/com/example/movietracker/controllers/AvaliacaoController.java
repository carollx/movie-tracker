package com.example.movietracker.controllers;

import com.example.movietracker.dtos.AvaliacaoDTO;
import com.example.movietracker.entities.Avaliacao;
import com.example.movietracker.services.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping
    public ResponseEntity<Avaliacao> cadastrarAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDTO) {
        Avaliacao avaliacaoCriada = avaliacaoService.salvarAvaliacao(avaliacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoCriada);
    }

    @GetMapping
    public ResponseEntity<List<Avaliacao>> listarTodasAvaliacoes() {
        List<Avaliacao> avaliacoes = avaliacaoService.listarTodasAvaliacoes();
        return ResponseEntity.ok(avaliacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> buscarAvaliacaoPorId(@PathVariable Long id) {
        Optional<Avaliacao> avaliacao = avaliacaoService.buscarAvaliacaoPorId(id);
        return avaliacao.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/filme/{filmeId}")
    public ResponseEntity<List<Avaliacao>> listarAvaliacoesPorFilme(@PathVariable Long filmeId) {
        List<Avaliacao> avaliacoes = avaliacaoService.listarAvaliacoesPorFilme(filmeId);
        return ResponseEntity.ok(avaliacoes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Avaliacao> atualizarAvaliacao(@PathVariable Long id, @RequestBody Avaliacao avaliacao) {
        Avaliacao avaliacaoAtualizada = avaliacaoService.atualizarAvaliacao(id, avaliacao);
        if (avaliacaoAtualizada != null) {
            return ResponseEntity.ok(avaliacaoAtualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAvaliacao(@PathVariable Long id) {
        avaliacaoService.deletarAvaliacao(id);
        return ResponseEntity.noContent().build();
    }
}
