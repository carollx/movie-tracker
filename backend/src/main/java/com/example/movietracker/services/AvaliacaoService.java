package com.example.movietracker.services;

import com.example.movietracker.dtos.AvaliacaoDTO;
import com.example.movietracker.entities.Avaliacao;
import com.example.movietracker.entities.Filme;
import com.example.movietracker.entities.User;
import com.example.movietracker.repositories.AvaliacaoRepository;
import com.example.movietracker.repositories.FilmeRepository;
import com.example.movietracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private UserRepository userRepository;

    public AvaliacaoDTO salvarAvaliacao(AvaliacaoDTO avaliacaoDTO) {
        User usuario = getUsuarioAtual();
        Optional<Filme> filme = filmeRepository.findByIdAndUsuarioId(avaliacaoDTO.getFilmeId(), usuario.getId());
        if (!filme.isPresent()) {
            throw new RuntimeException("Filme nao encontrado com ID: " + avaliacaoDTO.getFilmeId());
        }

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setFilme(filme.get());
        avaliacao.setNota(avaliacaoDTO.getNota());
        avaliacao.setComentario(avaliacaoDTO.getComentario());
        avaliacao.setUsuario(usuario);

        Avaliacao avaliacaoSalva = avaliacaoRepository.save(avaliacao);
        return toDTO(avaliacaoSalva);
    }

    public List<AvaliacaoDTO> listarAvaliacoes() {
        User usuario = getUsuarioAtual();
        return avaliacaoRepository.findByUsuarioIdOrderByIdDesc(usuario.getId())
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private User getUsuarioAtual() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario autenticado nao encontrado"));
    }

    private AvaliacaoDTO toDTO(Avaliacao avaliacao) {
        return new AvaliacaoDTO(
                avaliacao.getId(),
                avaliacao.getFilme().getId(),
                avaliacao.getNota(),
                avaliacao.getComentario()
        );
    }
}
