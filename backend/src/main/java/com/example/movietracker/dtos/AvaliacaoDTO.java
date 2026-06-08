package com.example.movietracker.dtos;

public class AvaliacaoDTO {
    private Long id;
    private Long filmeId;
    private Integer nota;
    private String comentario;

    public AvaliacaoDTO() {
    }

    public AvaliacaoDTO(Long filmeId, Integer nota, String comentario) {
        this.filmeId = filmeId;
        this.nota = nota;
        this.comentario = comentario;
    }

    public AvaliacaoDTO(Long id, Long filmeId, Integer nota, String comentario) {
        this.id = id;
        this.filmeId = filmeId;
        this.nota = nota;
        this.comentario = comentario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFilmeId() {
        return filmeId;
    }

    public void setFilmeId(Long filmeId) {
        this.filmeId = filmeId;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
