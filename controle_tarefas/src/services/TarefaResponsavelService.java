package services;

import dao.TarefaDAO;
import models.Tarefa;

import java.util.Scanner;

public class TarefaResponsavelService {
    private final TarefaDAO tarefaDAO = new TarefaDAO();

    public void cadastrarTarefa(Scanner scanner, int gerenteId) {
        System.out.println("\n=== Cadastro de Tarefa ===");

        System.out.print("Título da tarefa: ");
        String titulo = scanner.nextLine();

        System.out.print("Status (pendente, em progresso, concluido): ");
        String status = scanner.nextLine();

        System.out.print("ID da Categoria: ");
        int categoriaId = Integer.parseInt(scanner.nextLine());

        Tarefa tarefa = new Tarefa(titulo, status, gerenteId, categoriaId);
        boolean sucesso = tarefaDAO.cadastrarTarefa(tarefa);

        if (sucesso) {
            System.out.println("✅ Tarefa cadastrada com sucesso!");
        } else {
            System.out.println("❌ Falha ao cadastrar tarefa.");
        }
    }

    public void editarTarefa(Scanner scanner) {
        System.out.println("\n=== Editar Tarefa ===");

        System.out.print("ID da tarefa que deseja editar: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Novo título: ");
        String titulo = scanner.nextLine();

        System.out.print("Novo status (pendente, em progresso, concluido): ");
        String status = scanner.nextLine();

        System.out.print("Novo ID da categoria: ");
        int categoriaId = Integer.parseInt(scanner.nextLine());

        Tarefa tarefa = new Tarefa(id, titulo, status, 0, categoriaId); // gerenteId não é alterado
        boolean sucesso = tarefaDAO.editarTarefa(tarefa);

        if (sucesso) {
            System.out.println("✅ Tarefa atualizada com sucesso!");
        } else {
            System.out.println("❌ Falha ao atualizar tarefa.");
        }
    }
}

