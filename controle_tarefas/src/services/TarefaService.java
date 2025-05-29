package services;

public class TarefaService {
    public void associarTarefaAColaboradores(Scanner scanner) {
        try (Connection conn = Database.conectar()) {
            System.out.print("ID da tarefa que deseja associar: ");
            int tarefaId = Integer.parseInt(scanner.nextLine());

            System.out.print("Quantos colaboradores deseja associar? ");
            int qtd = Integer.parseInt(scanner.nextLine());

            String sql = "INSERT INTO tarefa_responsavel (tarefa_id, colaborador_id) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                for (int i = 0; i < qtd; i++) {
                    System.out.print("ID do colaborador " + (i + 1) + ": ");
                    int colaboradorId = Integer.parseInt(scanner.nextLine());

                    stmt.setInt(1, tarefaId);
                    stmt.setInt(2, colaboradorId);
                    stmt.addBatch();
                }

                stmt.executeBatch();
                System.out.println("Colaboradores associados à tarefa com sucesso.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao associar colaboradores à tarefa: " + e.getMessage());
        }
    }

}
