package com.example.movietracker.services;

import com.example.movietracker.entities.Filme;
import com.example.movietracker.entities.User;
import com.example.movietracker.repositories.FilmeRepository;
import com.example.movietracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private UserRepository userRepository;

    public Filme salvarFilme(Filme filme) {
        filme.setUsuario(getUsuarioAtual());
        return filmeRepository.save(filme);
    }

    public List<Filme> listarFilmes() {
        return filmeRepository.findByUsuarioId(getUsuarioAtual().getId());
    }

    public Filme marcarComoAssistido(Long id) {
        Optional<Filme> filme = filmeRepository.findByIdAndUsuarioId(id, getUsuarioAtual().getId());
        if (filme.isPresent()) {
            Filme filmeAtualizado = filme.get();
            filmeAtualizado.setAssistido(true);
            return filmeRepository.save(filmeAtualizado);
        }

        return null;
    }

    private User getUsuarioAtual() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario autenticado nao encontrado"));
    }
}
