CREATE TABLE IF NOT EXISTS gerente (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    email TEXT NOT NULL UNIQUE,
    senha TEXT NOT NULL
);
CREATE TABLE tarefas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(255),
    descricao TEXT,
    status VARCHAR(50),
    colaborador_id INT
);
