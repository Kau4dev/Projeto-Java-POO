package dao;

import java.sql.*;
import utils.conexao;

public class CategoriaDAO {
    public void listarCategorias() {
        String sql = "SELECT id, titulo FROM categoria";

        try (Connection conn = conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("=== Lista de Categorias ===");
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                System.out.printf("ID: %d | Título: %s%n", id, titulo);
            }
            System.out.println("==============================================================================");
        } catch (SQLException e) {
            System.out.println("Erro ao listar categorias: " + e.getMessage());
        }
    }
}
