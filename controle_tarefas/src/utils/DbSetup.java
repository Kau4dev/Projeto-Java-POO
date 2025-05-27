package utils;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DbSetup {
    public static void criarTabelas() {
        String sql = """
            CREATE TABLE IF NOT EXISTS gerente (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                email TEXT NOT NULL UNIQUE,
                senha TEXT NOT NULL
            );
        """;

        try (Connection conn = conexao.conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabelas criadas ou já existentes.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }
}
