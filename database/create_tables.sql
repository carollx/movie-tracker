CREATE DATABASE IF NOT EXISTS movie_tracker;

USE movie_tracker;

CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS filmes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    genero VARCHAR(100) NOT NULL,
    ano INT NOT NULL,
    descricao TEXT,
    assistido BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_filmes_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
        ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS avaliacoes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    filme_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    nota INT NOT NULL CHECK (nota BETWEEN 1 AND 5),
    comentario TEXT,
    CONSTRAINT fk_avaliacoes_filme
        FOREIGN KEY (filme_id) REFERENCES filmes(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_avaliacoes_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
        ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx_usuarios_email ON usuarios(email);
CREATE INDEX idx_filmes_usuario_id ON filmes(usuario_id);
CREATE INDEX idx_avaliacoes_filme_id ON avaliacoes(filme_id);
CREATE INDEX idx_avaliacoes_usuario_id ON avaliacoes(usuario_id);
