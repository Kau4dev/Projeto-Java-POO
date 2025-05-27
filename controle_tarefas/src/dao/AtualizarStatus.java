public boolean atualizarStatus(int idDaTarefa, String novoStatus) {

    String sql = "UPDATE tarefas SET status = ? WHERE id = ?";

    try {
       
        Connection conexao = conexao.conectar();

     
        PreparedStatement comando = conexao.prepareStatement(sql);
        comando.setString(1, novoStatus);   
        comando.setInt(2, idDaTarefa);      

        // Executa a atualização
        int resultado = comando.executeUpdate();

        // Se pelo menos 1 linha foi alterada, retorna true (sucesso)
        return resultado > 0;

    } catch (SQLException erro) {
        // Mostra o erro no console se der problema
        System.out.println("Erro ao atualizar o status da tarefa: " + erro.getMessage());
        return false;
    }
}


