package dao;

import java.sql.*;
import models.Gerente;
import utils.conexao;

public class GerenteDAO {
    public boolean cadastrar(Gerente gerente) {
        String sql = "INSERT INTO gerente (nome, email, senha) VALUES (?, ?, ?)";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, gerente.getNome());
            stmt.setString(2, gerente.getEmail());
            stmt.setString(3, gerente.getSenha());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar gerente: " + e.getMessage());
            return false;
        }
    }

    public Gerente login(String email, String senha) {
        String sql = "SELECT * FROM gerente WHERE email = ? AND senha = ?";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Gerente(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fazer login: " + e.getMessage());
        }
        return null;
    }
}
