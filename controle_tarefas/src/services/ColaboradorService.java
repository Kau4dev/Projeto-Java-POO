package services;

import java.util.List;
import java.util.Scanner;

import dao.ColaboradorDAO;
import dao.TarefaDAO;
import models.Colaborador;
import models.Tarefa;

public class ColaboradorService {
    public void cadastrarColaborador(Scanner scanner) {
        System.out.println("\n=== Cadastro de Colaborador ===");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        Colaborador colaborador = new Colaborador(nome, email, senha);

        ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
        boolean sucesso = colaboradorDAO.cadastrar(colaborador);

        if (sucesso) {
            System.out.println("✅ Colaborador cadastrado com sucesso!");
        } else {
            System.out.println("❌ Erro ao cadastrar colaborador.");
        }
    }

    public Colaborador loginColaborador(Scanner scanner) {
        System.out.println("\n=== Login de Colaborador ===");
        
        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
        Colaborador colaborador = colaboradorDAO.login(email, senha);

        if (colaborador != null) {
            System.out.println("✅ Login realizado com sucesso!");
            return colaborador;
        } else {
            System.out.println("❌ Email ou senha incorretos.");
            return null;
        }
    }
    
    public void listarColaboradores() {
        ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
        colaboradorDAO.listarColaboradores();
    }

    public void deletarColaborador(Scanner scanner) {
        System.out.print("Digite o ID do colaborador a ser deletado: ");
        int id = Integer.parseInt(scanner.nextLine());

        ColaboradorDAO colaboradorDAO = new ColaboradorDAO();
        boolean sucesso = colaboradorDAO.deletar(id);

        if (sucesso) {
            System.out.println("✅ Colaborador deletado com sucesso!");
        } else {
            System.out.println("❌ Colaborador não encontrado ou erro ao deletar.");
        }
    }

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
        System.out.print("ID do status: \n");

        System.out.println("1 - Pendente");
        System.out.println("2 - Em andamento");
        System.out.println("3 - Concluída");

        String novoStatus = scanner.nextLine();
        switch (novoStatus) {
            case "1":
                novoStatus = "pendente";
                break;
            case "2":
                novoStatus = "em progresso";
                break;
            case "3":
                novoStatus = "concluido";
                break;
            default:
                System.out.println("Status inválido. Tente novamente.");
                return;
        }
        TarefaDAO tarefaDAO = new TarefaDAO();
        boolean sucesso = tarefaDAO.atualizarStatus(idTarefa, novoStatus);

        if (sucesso) {
            System.out.println("✅ Status atualizado com sucesso!");
        } else {
            System.out.println("❌ Erro ao atualizar status.");
        }
    }
}
