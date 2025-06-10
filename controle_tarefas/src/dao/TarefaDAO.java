package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Categoria;
import models.Tarefa;
import services.CategoriaService;
import utils.conexao;
import models.*;
import services.ColaboradorService;



public class TarefaDAO {
    private final CategoriaService categoriaService = new CategoriaService();
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

    public List<Tarefa> listarPorColaborador(int colaboradorId) {
        List<Tarefa> lista = new ArrayList<>();
        String sql = "SELECT * FROM tarefa WHERE colaborador_id = ?";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, colaboradorId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getInt("id"));
                tarefa.setTitulo(rs.getString("titulo"));
                tarefa.setStatus(rs.getString("status"));
                tarefa.setGerenteId(rs.getInt("gerente_id"));
                tarefa.setCategoriaId(rs.getInt("categoria_id"));
                lista.add(tarefa);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar tarefas: " + e.getMessage());
        }

        return lista;
    }

    public boolean atualizarStatus(int tarefaId, String novoStatus) {
        String sql = "UPDATE tarefa SET status = ? WHERE id = ?";

        try (Connection conn = conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoStatus);
            stmt.setInt(2, tarefaId);

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar status: " + e.getMessage());
            return false;
        }
    }

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

    public List<Tarefa> listarTodas() {
    List<Tarefa> lista = new ArrayList<>();
    String sql = "SELECT tarefa.*, categoria.titulo AS titulo_categoria FROM tarefa " +
                 "JOIN categoria ON tarefa.categoria_id = categoria.id";

    try (Connection conn = conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Tarefa tarefa = new Tarefa();
            tarefa.setId(rs.getInt("id"));
            tarefa.setTitulo(rs.getString("titulo"));
            tarefa.setStatus(rs.getString("status"));
            tarefa.setGerenteId(rs.getInt("gerente_id"));
            tarefa.setCategoriaId(rs.getInt("categoria_id"));

            Categoria categoria = new Categoria();
            categoria.setId(rs.getInt("categoria_id")); 
            categoria.setTitulo(rs.getString("titulo_categoria"));
            tarefa.setCategoria(categoria);

            lista.add(tarefa);
        }

    } catch (SQLException e) {
        System.out.println("Erro ao listar todas as tarefas: " + e.getMessage());
    }

    return lista;
}

}

