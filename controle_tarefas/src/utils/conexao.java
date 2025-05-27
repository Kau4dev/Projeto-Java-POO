package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexao {
    private static final String URL = "jdbc:sqlite:controle_tarefas/database/controle_tarefas.db";

    public static Connection conectar() {
        try {
            Connection conn = DriverManager.getConnection(URL);
            System.out.println("Conectado com sucesso ao banco SQLite!");
            return conn;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            return null;
        }
    }
}
