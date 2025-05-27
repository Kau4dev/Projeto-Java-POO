package utils;

import java.sql.Connection;
import java.sql.Statement;

public class DbSetup {
    public static void criarTabelas() {
        try (Connection conn = conexao.conectar();
             Statement stmt = conn.createStatement()) {

            String sql = """
    CREATE TABLE IF NOT EXISTS gerente (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        nome TEXT NOT NULL,
        email TEXT NOT NULL UNIQUE,
        pin INTEGER NOT NULL
    );

    CREATE TABLE IF NOT EXISTS colaborador (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        nome TEXT NOT NULL,
        email TEXT NOT NULL UNIQUE,
        pin INTEGER NOT NULL
    );

    CREATE TABLE IF NOT EXISTS categoria (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        titulo TEXT NOT NULL
    );

    CREATE TABLE IF NOT EXISTS tarefa (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        titulo TEXT NOT NULL,
        status TEXT NOT NULL CHECK (status IN ('em progresso', 'pendente', 'concluido')),
        gerente_id INTEGER,
        categoria_id INTEGER,
        FOREIGN KEY (gerente_id) REFERENCES gerente(id),
        FOREIGN KEY (categoria_id) REFERENCES categoria(id)
    );

    CREATE TABLE IF NOT EXISTS tarefa_responsavel (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        colaborador_id INTEGER,
        tarefa_id INTEGER,
        FOREIGN KEY (colaborador_id) REFERENCES colaborador(id),
        FOREIGN KEY (tarefa_id) REFERENCES tarefa(id)
    );
""";


            stmt.executeUpdate(sql);
            System.out.println("Tabelas criadas.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
