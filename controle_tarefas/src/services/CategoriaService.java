package services;

import utils.DbSetup;

import java.sql.*;

public class CategoriaService {

    public void listarCategorias() {
        String sql = "SELECT id, titulo FROM categorias";
        try (Connection conn = DbSetup.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("=== Lista de Categorias ===");
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                System.out.printf("ID: %d | Título: %s%n", id, titulo);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar categorias: " + e.getMessage());
        }
    }
}
