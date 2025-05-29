package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}
