import java.util.Scanner;


import dao.GerenteDAO;
import models.Gerente;
import utils.DbSetup;

import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean isLoggedIn = true;
        DbSetup.criarTabelas();

        while (isLoggedIn) {
            System.out.print("==============================================================================\n");
            System.out.print("Controle de Tarefas\n");
            System.out.print("faça:\n1-login\n2-cadastro\n3-sair\n");
            System.out.print("==============================================================================\n");

            System.out.print("Digite sua escolha: ");
            int num1 = -1;
            try {
                num1 = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Entrada inválida. Por favor, digite um número.\n");
                scanner.nextLine(); 
                continue; 
            }

            switch (num1) {
                case 1:
                    System.out.print("Digite o email: ");
                    String emailLogin = scanner.next();

                    System.out.print("Digite a senha: ");
                    String senhaLogin = scanner.next();

                    GerenteDAO gerenteDAO = new GerenteDAO();
                    Gerente gerenteLogado = gerenteDAO.login(emailLogin, senhaLogin);

                    if (gerenteLogado != null) {
                        System.out.println("Login bem-sucedido. Bem-vindo, " + gerenteLogado.getNome() + "!");
                        // Aqui você pode ir para o menu do sistema
                    } else {
                        System.out.println("Email ou senha inválidos.");
                    }
                    break;

                case 2:
                    System.out.print("Digite o nome: ");
                    String nome = scanner.next();

                    System.out.print("Digite o email: ");
                    String emailCadastro = scanner.next();

                    System.out.print("Digite a senha: ");
                    String senhaCadastro = scanner.next();

                    Gerente novoGerente = new Gerente(nome, emailCadastro, senhaCadastro);
                    GerenteDAO dao = new GerenteDAO();

                    if (dao.cadastrar(novoGerente)) {
                        System.out.println("Cadastro realizado com sucesso!");
                    } else {
                        System.out.println("Erro ao cadastrar.");
                    }
                    break;

                case 3:
                    System.out.print("Você escolheu sair.\n");
                    isLoggedIn = false; // Sair do loop
                    break;
                default:
                    System.out.print("Opção inválida. Tente novamente.\n");
            }
        }

        scanner.close();
    }
}