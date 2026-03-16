CREATE DATABASE movie_tracker;

USE movie_tracker;

CREATE TABLE filmes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    genero VARCHAR(100),
    ano INT,
    descricao TEXT,
    assistido BOOLEAN DEFAULT FALSE
);

CREATE TABLE avaliacoes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    filme_id BIGINT,
    nota INT CHECK (nota BETWEEN 1 AND 5),
    comentario TEXT,
    FOREIGN KEY (filme_id) REFERENCES filmes(id)
);