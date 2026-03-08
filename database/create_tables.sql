CREATE TABLE filmes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255),
    genero VARCHAR(100),
    ano INT,
    descricao TEXT,
    assistido BOOLEAN
);

CREATE TABLE avaliacoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    filme_id INT,
    nota INT,
    comentario TEXT,
    FOREIGN KEY (filme_id) REFERENCES filmes(id)
);
