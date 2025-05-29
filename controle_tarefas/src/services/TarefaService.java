package services;

import java.util.Scanner;

import dao.TarefaDAO;

public class TarefaService {
    public void conectarTarefaAColaboradores(Scanner scanner, int gerenteId) {
        System.out.print("Digite o ID da Tarefa:");
        int tarefaId = Integer.parseInt(scanner.nextLine());

        System.out.print("Digite os IDs dos colaboradores separados por vírgula: ");
        String[] idsColaboradores = scanner.nextLine().split(",");

        for (String idStr : idsColaboradores) {
            try {
                int colaboradorId = Integer.parseInt(idStr.trim());

                // Simulando a conexão:
                System.out.println("Conectando Colaborador ID " + colaboradorId + " à Tarefa ID " + tarefaId);

                // Aqui você poderia adicionar lógica de adicionar em uma lista ou coleção se quiser.

            } catch (NumberFormatException e) {
                System.out.println("ID inválido: " + idStr.trim());
            }
        }

        System.out.println("Conexão concluída.");
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
}
