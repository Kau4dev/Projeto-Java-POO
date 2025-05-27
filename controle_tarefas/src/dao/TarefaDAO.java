package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Tarefa;
import utils.conexao;

public class TarefaDAO {

    // Método para listar as tarefas de um colaborador pelo ID
    public List<Tarefa> listarPorColaborador(int colaboradorId) {
        List<Tarefa> lista = new ArrayList<>();
        String sql = "SELECT * FROM tarefas WHERE colaborador_id = ?";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, colaboradorId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getInt("id"));
                tarefa.setTitulo(rs.getString("titulo"));
                tarefa.setDescricao(rs.getString("descricao"));
                tarefa.setStatus(rs.getString("status"));
                tarefa.setColaboradorId(rs.getInt("colaborador_id"));
                lista.add(tarefa);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar tarefas: " + e.getMessage());
        }

        return lista;
    }

    // Método para atualizar o status da tarefa
    public boolean atualizarStatus(int idDaTarefa, String novoStatus) {
        String sql = "UPDATE tarefas SET status = ? WHERE id = ?";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoStatus);
            stmt.setInt(2, idDaTarefa);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar status: " + e.getMessage());
            return false;
        }
    }

    // Método main para testar os métodos acima
    public static void main(String[] args) {
        TarefaDAO tarefaDAO = new TarefaDAO();

        // 1. Buscar tarefas do colaborador com ID 3
        List<Tarefa> tarefas = tarefaDAO.listarPorColaborador(3);
        for (Tarefa t : tarefas) {
            System.out.println(t.getId() + " - " + t.getTitulo() + " - " + t.getStatus());
        }

        // 2. Atualizar o status da tarefa com ID 2
        boolean sucesso = tarefaDAO.atualizarStatus(2, "Concluída");

        if (sucesso) {
            System.out.println("Status atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar status.");
        }
    }
}

