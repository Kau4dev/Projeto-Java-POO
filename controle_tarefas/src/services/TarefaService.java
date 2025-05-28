package services;

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

}
