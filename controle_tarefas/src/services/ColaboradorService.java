package services;

import java.util.Scanner;
import dao.ColaboradorDAO;
import models.Colaborador;

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
    
}