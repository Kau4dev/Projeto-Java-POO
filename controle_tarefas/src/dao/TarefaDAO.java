package dao;

import models.Tarefa;
import utils.conexao;

import java.sql.*;

public class TarefaDAO {
    public boolean cadastrarTarefa(Tarefa tarefa) {
        String sql = "INSERT INTO tarefa (titulo, status, gerente_id, categoria_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getStatus());
            stmt.setInt(3, tarefa.getGerenteId());
            stmt.setInt(4, tarefa.getCategoriaId());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar tarefa: " + e.getMessage());
            return false;
        }
    }

    public boolean editarTarefa(Tarefa tarefa) {
        String sql = "UPDATE tarefa SET titulo = ?, status = ?, categoria_id = ? WHERE id = ?";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tarefa.getTitulo());
            stmt.setString(2, tarefa.getStatus());
            stmt.setInt(3, tarefa.getCategoriaId());
            stmt.setInt(4, tarefa.getId());

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao editar tarefa: " + e.getMessage());
            return false;
        }
    }
}

