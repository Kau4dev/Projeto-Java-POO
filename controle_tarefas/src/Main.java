import java.util.Scanner;

import models.Colaborador;
import models.Gerente;
import services.ColaboradorService;
import services.GerenteService;
import services.TarefaService;
import utils.DbSetup;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GerenteService gerenteService = new GerenteService();
    private static final ColaboradorService colaboradorService = new ColaboradorService();
    private static final TarefaService tarefaService = new TarefaService();

    public static void main(String[] args) {
        DbSetup.criarTabelas();

        boolean isRunning = true;

        while (isRunning) {
            exibirMenuPrincipal();
            int escolha = lerInteiro();

            switch (escolha) {
                case 1 -> realizarLogin();
                case 2 -> realizarCadastro();
                case 3 -> {
                    System.out.println("Você escolheu sair.");
                    isRunning = false;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("==============================================================================");
        System.out.println("Controle de Tarefas");
        System.out.println("Faça:");
        System.out.println("1 - Login");
        System.out.println("2 - Cadastro");
        System.out.println("3 - Sair");
        System.out.println("==============================================================================");
        System.out.print("Digite sua escolha: ");
    }

    private static void realizarLogin() {
        System.out.println("=== Sistema de Login ===");
        System.out.println("Qual tipo de usuário?");
        System.out.println("1 - Gerente");
        System.out.println("2 - Colaborador");
        System.out.print("Digite: ");

        int tipoUsuario = lerInteiro();

        switch (tipoUsuario) {
            case 1 -> {
                Gerente gerente = gerenteService.loginGerente(scanner);
                if (gerente == null) {
                    System.out.println("Login falhou. Verifique seu email e senha.");
                    return;
                }
                System.out.println("=== Bem-Vindo, " + gerente.getNome() + " ===");
                exibirMenuGerente(gerente);
            }
            case 2 -> {
                Colaborador colaborador = colaboradorService.loginColaborador(scanner);
                if (colaborador == null) {
                    System.out.println("Login falhou. Verifique seu email e senha.");
                    return;
                }
                System.out.println("=== Bem-Vindo, " + colaborador.getNome() + " ===");
                exibirMenuColaborador(colaborador);
            }
            default -> System.out.println("Tipo de usuário inválido.");
        }
    }

    private static void exibirMenuGerente(Gerente gerente) {
        while (true) {
            System.out.println("""
                Funcionalidades disponíveis:
                1 - Cadastrar Colaborador
                2 - Cadastrar Tarefa
                3 - Editar Tarefa
                4 - Visualizar Tarefas
                5 - Visualizar Colaboradores
                6 - Deletar Tarefa
                7 - Deletar Colaborador
                8 - Visualizar Categorias
                9 - Sair
                """);
            System.out.print("Digite sua escolha: ");
            int escolha = lerInteiro();

            switch (escolha) {
                case 1 -> colaboradorService.cadastrarColaborador(scanner);
                // case 2 -> tarefaService.cadastrarTarefa(scanner, gerente.getId());
                // case 3 -> tarefaService.editarTarefa(scanner, gerente.getId());
                // case 4 -> tarefaService.visualizarTarefas();
                // case 5 -> colaboradorService.visualizarColaboradores();
                case 6 -> tarefaService.deletarTarefa(scanner);
                case 7 -> colaboradorService.deletarColaborador(scanner);
                // case 8 -> tarefaService.visualizarCategorias();
                case 9 -> {
                    System.out.println("Você escolheu sair.");
                    return;
                }
                default -> System.out.println("Opção inválida ou não implementada.");
            }
        }
    }

    private static void exibirMenuColaborador(Colaborador colaborador) {
        while (true) {
            System.out.println("""
                Funcionalidades disponíveis:
                1 - Visualizar Tarefas
                2 - Editar Status de Tarefa
                3 - Sair
                """);
            System.out.print("Digite sua escolha: ");
            int escolha = lerInteiro();

            switch (escolha) {
                // case 1 -> tarefaService.visualizarTarefasPorColaborador(scanner, colaborador.getId());
                // case 2 -> tarefaService.atualizarStatusTarefa(scanner, colaborador.getId());
                case 3 -> {
                    System.out.println("Você escolheu sair.");
                    return;
                }
                default -> System.out.println("Opção inválida ou não implementada.");
            }
        }
    }

    private static void realizarCadastro() {
        System.out.println("=== Sistema de Cadastro ===");
        System.out.println("Qual tipo de usuário?");
        System.out.println("1 - Gerente");
        System.out.println("2 - Colaborador");
        System.out.print("Digite: ");

        int tipo = lerInteiro();

        switch (tipo) {
            case 1 -> gerenteService.cadastrarGerente(scanner);
            case 2 -> colaboradorService.cadastrarColaborador(scanner);
            default -> System.out.println("Tipo de usuário inválido.");
        }
    }

    private static int lerInteiro() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.print("Entrada vazia. Digite um número: ");
                    continue;
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Digite um número: ");
            } catch (Exception e) {
                System.out.println("Erro inesperado ao ler número: " + e.getMessage());
                return -1;
            }
        }
    }
}