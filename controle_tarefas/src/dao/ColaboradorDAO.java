public List<Tarefa> listarPorColaborador(int colaboradorId) {
    List<Tarefa> lista = new ArrayList<>();
    String sql = "SELECT * FROM tarefas WHERE colaborador_id = ?";

    try (Connection conn = conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, colaboradorId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Tarefa tarefa = new Tarefa();
            tarefa.setId(rs.getInt("id"));
            tarefa.setTitulo(rs.getString("titulo"));
            tarefa.setDescricao(rs.getString("descricao"));
            tarefa.setStatus(rs.getString("status"));
            tarefa.setColaboradorId(rs.getInt("colaborador_id"));
            lista.add(tarefa);
        }

    } catch (SQLException e) {
        System.out.println("Erro ao buscar tarefas: " + e.getMessage());
    }

    return lista;
}
