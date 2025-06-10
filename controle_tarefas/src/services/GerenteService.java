package services;

import java.util.Scanner;

import dao.GerenteDAO;
import models.Gerente;

public class GerenteService {
     public void cadastrarGerente(Scanner scanner) {
        System.out.println("\n=== Cadastro de Gerente ===");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();
      
        Gerente gerente = new Gerente(nome, email, senha);

        GerenteDAO gerenteDAO = new GerenteDAO();
        boolean sucesso = gerenteDAO.cadastrar(gerente);

        if (sucesso) {
            System.out.println("✅ Gerente cadastrado com sucesso!");
        } else {
            System.out.println("❌ Erro ao cadastrar gerente.");
        }
    }
    
    public Gerente loginGerente(Scanner scanner) {
        System.out.println("=== Login de Gerente ===\n");

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        GerenteDAO gerenteDAO = new GerenteDAO();
        Gerente gerente = gerenteDAO.login(email, senha);

        if (gerente != null) {
            System.out.println("✅ Login realizado com sucesso!");
            return gerente;
        } else {
            System.out.println("❌ Email ou senha incorretos.");
            return null;
        }
    }
}
