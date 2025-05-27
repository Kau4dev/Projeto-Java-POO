import java.util.Scanner;
import dao.TarefaDAO;

public class AtualizarStatus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TarefaDAO tarefaDAO = new TarefaDAO();

        System.out.print("Digite o ID da tarefa: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Digite o novo status: ");
        String status = scanner.nextLine();

        boolean sucesso = tarefaDAO.atualizarStatus(id, status);

        if (sucesso) {
            System.out.println("✅ Status atualizado com sucesso!");
        } else {
            System.out.println("❌ Falha ao atualizar status.");
        }

        scanner.close();
    }
}
