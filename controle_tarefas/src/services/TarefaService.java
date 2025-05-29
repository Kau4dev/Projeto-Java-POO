package services;

import java.util.List;
import java.util.Scanner;

import dao.TarefaDAO;
import models.Tarefa;
import models.Categoria;
import dao.CategoriaDAO;


public class TarefaService {
    public void associarTarefaAColaboradores(Scanner scanner) {
        System.out.print("ID da tarefa que deseja associar: ");
        int tarefaId = Integer.parseInt(scanner.nextLine());

        System.out.print("Quantos colaboradores deseja associar? ");
        int qtd = Integer.parseInt(scanner.nextLine());

        TarefaDAO tarefaDAO = new TarefaDAO();
        boolean sucesso = tarefaDAO.associarColaboradores(tarefaId, scanner, qtd);

        if (sucesso) {
            System.out.println("✅ Colaboradores associados à tarefa com sucesso!");
        } else {
            System.out.println("❌ Erro ao associar colaboradores à tarefa.");
        }
    }

    public void deletarTarefa(Scanner scanner) {
        System.out.print("Digite o ID da tarefa a ser deletada: ");
        int id = Integer.parseInt(scanner.nextLine());

        TarefaDAO tarefaDAO = new TarefaDAO();
        boolean sucesso = tarefaDAO.deletar(id);

        if (sucesso) {
            System.out.println("✅ Tarefa deletada com sucesso!");
        } else {
            System.out.println("❌ Tarefa não encontrada ou erro ao deletar.");
        }
    }

    public void visualizarTarefas() {
        TarefaDAO tarefaDAO = new TarefaDAO();
        List<Tarefa> tarefas = tarefaDAO.listarTodas();

        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada.");
        } else {
            System.out.println("=== Lista de Tarefas ===");
            for (Tarefa tarefa : tarefas) {
                System.out.println(
                    "ID: " + tarefa.getId() +
                    " | Título: " + tarefa.getTitulo() +
                    " | Status: " + tarefa.getStatus()
                );
            }
            System.out.println("==============================================================================");
        }
    }

    public void visualizarTarefasPorColaborador(Scanner scanner, int colaboradorId) {
        TarefaDAO tarefaDAO = new TarefaDAO();
        List<Tarefa> tarefas = tarefaDAO.listarPorColaborador(colaboradorId);

        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada para o colaborador.");
        } else {
            System.out.println("=== Tarefas do Colaborador ===");
            for (Tarefa tarefa : tarefas) {
                System.out.println(
                    "ID: " + tarefa.getId() +
                    " | Título: " + tarefa.getTitulo() +
                    " | Status: " + tarefa.getStatus()
                );
            }
        }
    }

    public void atualizarStatusTarefa(Scanner scanner, int colaboradorId) {
        System.out.println("\n=== Atualizar Status da Tarefa ===");

        System.out.print("ID da tarefa: ");
        int idTarefa = Integer.parseInt(scanner.nextLine());

        System.out.print("Novo status (Ex: Pendente, Em andamento, Concluída): ");
        String novoStatus = scanner.nextLine();

        TarefaDAO tarefaDAO = new TarefaDAO();
        boolean sucesso = tarefaDAO.atualizarStatus(idTarefa, novoStatus);

        if (sucesso) {
            System.out.println("✅ Status atualizado com sucesso!");
        } else {
            System.out.println("❌ Erro ao atualizar status.");
        }
    }
}
