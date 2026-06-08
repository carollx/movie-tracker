package com.example.movietracker.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "filmes")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIgnore
    private User usuario;

    public Filme() {
    }

    public Filme(String titulo, String genero, Integer ano, String descricao, Boolean assistido, User usuario) {
        this.titulo = titulo;
        this.genero = genero;
        this.ano = ano;
        this.descricao = descricao;
        this.assistido = assistido;
        this.usuario = usuario;
    }

    public Filme(Long id, String titulo, String genero, Integer ano, String descricao, Boolean assistido, User usuario) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.ano = ano;
        this.descricao = descricao;
        this.assistido = assistido;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getAssistido() {
        return assistido;
    }

    public void setAssistido(Boolean assistido) {
        this.assistido = assistido;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
}
