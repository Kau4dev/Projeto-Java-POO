package services;

import java.util.List;
import java.util.Scanner;

import dao.TarefaDAO;
import models.Tarefa;

public class ColaboradorService {

    public void consultarTarefas(Scanner scanner, int colaboradorId) {
        System.out.println("\n=== Tarefas do Colaborador ===");

        TarefaDAO tarefaDAO = new TarefaDAO();
        List<Tarefa> tarefas = tarefaDAO.listarPorColaborador(colaboradorId);

        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa encontrada.");
        } else {
            for (Tarefa tarefa : tarefas) {
                System.out.println(
                    "ID: " + tarefa.getId() +
                    " | Título: " + tarefa.getTitulo() +
                    " | Status: " + tarefa.getStatus()
                );
            }
        }
    }

    public void atualizarStatusTarefa(Scanner scanner) {
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
