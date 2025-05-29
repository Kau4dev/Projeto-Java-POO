package services;

import java.util.Scanner;

import dao.TarefaDAO;

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
}
