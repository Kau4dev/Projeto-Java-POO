import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import dao.GerenteDAO;
import models.Gerente;
import services.ColaboradorService;
import utils.DbSetup;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DbSetup.criarTabelas();

        boolean isLoggedIn = true;

        while (isLoggedIn) {
            System.out.println("==============================================================================");
            System.out.println("Controle de Tarefas");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastro");
            System.out.println("3 - Sair");
            System.out.println("==============================================================================");

            System.out.print("Digite sua escolha: ");
            int escolha = -1;
            try {
                escolha = scanner.nextInt();
                scanner.nextLine(); // limpar buffer
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número.");
                scanner.nextLine(); // limpar buffer
                continue;
            }

            switch (escolha) {
                case 1:
                    // Login
                    System.out.print("Digite o email: ");
                    String emailLogin = scanner.nextLine();

                    System.out.print("Digite a senha: ");
                    String senhaLogin = scanner.nextLine();

                    GerenteDAO gerenteDAO = new GerenteDAO();
                    Gerente gerenteLogado = gerenteDAO.login(emailLogin, senhaLogin);

                    if (gerenteLogado != null) {
                        System.out.println("✅ Login realizado com sucesso! Bem-vindo(a), " + gerenteLogado.getNome());

                        // Menu colaborador (após login do gerente)
                        ColaboradorService colaboradorService = new ColaboradorService();
                        System.out.print("Informe o ID do colaborador para gerenciar: ");
                        int colaboradorId = Integer.parseInt(scanner.nextLine());

                        boolean menuAberto = true;
                        while (menuAberto) {
                            System.out.println("\n=== Menu do Colaborador ===");
                            System.out.println("1 - Ver tarefas");
                            System.out.println("2 - Atualizar status de tarefa");
                            System.out.println("0 - Sair");
                            System.out.print("Escolha uma opção: ");
                            String opcao = scanner.nextLine();

                            switch (opcao) {
                                case "1":
                                    colaboradorService.consultarTarefas(scanner, colaboradorId);
                                    break;
                                case "2":
                                    colaboradorService.atualizarStatusTarefa(scanner);
                                    break;
                                case "0":
                                    menuAberto = false;
                                    break;
                                default:
                                    System.out.println("Opção inválida.");
                            }
                        }

                    } else {
                        System.out.println("❌ Email ou senha incorretos.");
                    }
                    break;

                case 2:
                    // Cadastro
                    System.out.print("Digite o nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Digite o email: ");
                    String emailCadastro = scanner.nextLine();

                    System.out.print("Digite a senha: ");
                    String senhaCadastro = scanner.nextLine();

                    Gerente novoGerente = new Gerente(nome, emailCadastro, senhaCadastro);
                    GerenteDAO dao = new GerenteDAO();

                    if (dao.cadastrar(novoGerente)) {
                        System.out.println("✅ Cadastro realizado com sucesso!");
                    } else {
                        System.out.println("❌ Erro ao cadastrar.");
                    }
                    break;

                case 3:
                    System.out.println("Você escolheu sair.");
                    isLoggedIn = false;
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}
