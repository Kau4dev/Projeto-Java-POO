package dao;

import java.sql.*;
import models.Colaborador;
import utils.conexao;


public class ColaboradorDAO {
    public boolean cadastrar(Colaborador colaborador) {
        String sql = "INSERT INTO colaborador (nome, email, pin) VALUES (?, ?, ?)";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, colaborador.getNome());
            stmt.setString(2, colaborador.getEmail());
            stmt.setString(3, colaborador.getPin());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar colaborador: " + e.getMessage());
            return false;
        }
    }

    public Colaborador login(String email, String pin) {
        String sql = "SELECT * FROM colaborador WHERE email = ? AND pin = ?";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, pin);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Colaborador(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("pin")
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fazer login: " + e.getMessage());
        }
        return null;
    }
}
