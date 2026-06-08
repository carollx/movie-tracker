package com.example.movietracker.controllers;

import com.example.movietracker.dtos.AvaliacaoDTO;
import com.example.movietracker.services.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping
    public ResponseEntity<AvaliacaoDTO> cadastrarAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDTO) {
        AvaliacaoDTO avaliacaoCriada = avaliacaoService.salvarAvaliacao(avaliacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(avaliacaoCriada);
    }

    @GetMapping
    public ResponseEntity<List<AvaliacaoDTO>> listarAvaliacoes() {
        List<AvaliacaoDTO> avaliacoes = avaliacaoService.listarAvaliacoes();
        return ResponseEntity.ok(avaliacoes);
    }
}
