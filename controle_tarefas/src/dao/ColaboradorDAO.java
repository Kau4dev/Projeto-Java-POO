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

    public boolean deletar(int id) {
        String sql = "DELETE FROM colaborador WHERE id = ?";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar colaborador: " + e.getMessage());
            return false;
        }
    }

    public void listarColaboradores() {
        String sql = "SELECT id, nome, email FROM colaborador";

        try (Connection conn = conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("=== Lista de Colaboradores ===");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                System.out.printf("ID: %d | Nome: %s | Email: %s%n", id, nome, email);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar colaboradores: " + e.getMessage());
        }
    }
}
