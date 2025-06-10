package models;

public class Tarefa {
    private int id;
    private String titulo;
    private String descricao;
    private String status;
    private int colaboradorId;
    private int gerenteId;
    private int categoriaId;
    private Categoria categoria;

    public Tarefa() {}

    public Tarefa(int id, String titulo, String descricao, String status, int colaboradorId) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.colaboradorId = colaboradorId;
    }

    public Tarefa(String titulo, String status, int gerenteId, int categoriaId) {
        this.titulo = titulo;
        this.status = status;
        this.gerenteId = gerenteId;
        this.categoriaId = categoriaId;
    }

    public Tarefa(int id, String titulo, String status, int gerenteId, int categoriaId) {
        this.id = id;
        this.titulo = titulo;
        this.status = status;
        this.gerenteId = gerenteId;
        this.categoriaId = categoriaId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getColaboradorId() { return colaboradorId; }
    public void setColaboradorId(int colaboradorId) { this.colaboradorId = colaboradorId; }

    public int getGerenteId() { return gerenteId; }
    public void setGerenteId(int gerenteId) { this.gerenteId = gerenteId; }

    public int getCategoriaId() { return categoriaId; }
    public void setCategoriaId(int categoriaId) { this.categoriaId = categoriaId; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}
