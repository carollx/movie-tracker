package com.example.movietracker.services;

import com.example.movietracker.entities.Filme;
import com.example.movietracker.repositories.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    public Filme salvarFilme(Filme filme) {
        return filmeRepository.save(filme);
    }

    public List<Filme> listarTodosFilmes() {
        return filmeRepository.findAll();
    }

    public Optional<Filme> buscarFilmePorId(Long id) {
        return filmeRepository.findById(id);
    }

    public List<Filme> listarFilmesAssistidos() {
        return filmeRepository.findByAssistido(true);
    }

    public List<Filme> listarFilmesNaoAssistidos() {
        return filmeRepository.findByAssistido(false);
    }

    public List<Filme> listarFilmesPorGenero(String genero) {
        return filmeRepository.findByGenero(genero);
    }

    public Filme marcarComoAssistido(Long id) {
        Optional<Filme> filme = filmeRepository.findById(id);
        if (filme.isPresent()) {
            Filme f = filme.get();
            f.setAssistido(true);
            return filmeRepository.save(f);
        }
        return null;
    }

    public Filme atualizarFilme(Long id, Filme filmeAtualizado) {
        Optional<Filme> filme = filmeRepository.findById(id);
        if (filme.isPresent()) {
            Filme f = filme.get();
            f.setTitulo(filmeAtualizado.getTitulo());
            f.setGenero(filmeAtualizado.getGenero());
            f.setAno(filmeAtualizado.getAno());
            f.setDescricao(filmeAtualizado.getDescricao());
            return filmeRepository.save(f);
        }
        return null;
    }

    public void deletarFilme(Long id) {
        filmeRepository.deleteById(id);
    }
}
