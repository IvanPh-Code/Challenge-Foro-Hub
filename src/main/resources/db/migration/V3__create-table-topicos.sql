--
CREATE TABLE topicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    titulo VARCHAR(255) NOT NULL UNIQUE,
    mensaje VARCHAR(255) NOT NULL UNIQUE,
    fecha_creacion DATE NOT NULL,
    status VARCHAR(30) NOT NULL,
    autor_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES usuarios(id),
    FOREIGN KEY(curso_id) REFERENCES cursos(id)
);