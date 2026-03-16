package com.example.movietracker.services;

import com.example.movietracker.dtos.AvaliacaoDTO;
import com.example.movietracker.entities.Avaliacao;
import com.example.movietracker.entities.Filme;
import com.example.movietracker.repositories.AvaliacaoRepository;
import com.example.movietracker.repositories.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    public Avaliacao salvarAvaliacao(AvaliacaoDTO avaliacaoDTO) {
        Optional<Filme> filme = filmeRepository.findById(avaliacaoDTO.getFilmeId());
        if (!filme.isPresent()) {
            throw new RuntimeException("Filme não encontrado com ID: " + avaliacaoDTO.getFilmeId());
        }

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setFilme(filme.get());
        avaliacao.setNota(avaliacaoDTO.getNota());
        avaliacao.setComentario(avaliacaoDTO.getComentario());

        return avaliacaoRepository.save(avaliacao);
    }

    public List<Avaliacao> listarTodasAvaliacoes() {
        return avaliacaoRepository.findAll();
    }

    public Optional<Avaliacao> buscarAvaliacaoPorId(Long id) {
        return avaliacaoRepository.findById(id);
    }

    public List<Avaliacao> listarAvaliacoesPorFilme(Long filmeId) {
        return avaliacaoRepository.findByFilmeId(filmeId);
    }

    public Avaliacao atualizarAvaliacao(Long id, Avaliacao avaliacaoAtualizada) {
        Optional<Avaliacao> avaliacao = avaliacaoRepository.findById(id);
        if (avaliacao.isPresent()) {
            Avaliacao a = avaliacao.get();
            a.setNota(avaliacaoAtualizada.getNota());
            a.setComentario(avaliacaoAtualizada.getComentario());
            return avaliacaoRepository.save(a);
        }
        return null;
    }

    public void deletarAvaliacao(Long id) {
        avaliacaoRepository.deleteById(id);
    }
}
