package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import utils.conexao;

public class TarefaDAO {
    
    public boolean deletar(int id) {
        String sql = "DELETE FROM tarefa WHERE id = ?";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao deletar tarefa: " + e.getMessage());
            return false;
        }
    }

    public boolean associarColaboradores(int tarefaId, Scanner scanner, int qtd) {
        String sql = "INSERT INTO tarefa_responsavel (tarefa_id, colaborador_id) VALUES (?, ?)";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < qtd; i++) {
                System.out.print("ID do colaborador " + (i + 1) + ": ");
                int colaboradorId = Integer.parseInt(scanner.nextLine());

                stmt.setInt(1, tarefaId);
                stmt.setInt(2, colaboradorId);
                stmt.addBatch();
            }

            stmt.executeBatch();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao associar colaboradores à tarefa: " + e.getMessage());
            return false;
        }
    }
}
