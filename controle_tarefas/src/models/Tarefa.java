package models;

public class Tarefa {
    private int id;
    private String titulo;
    private String status;
    private int gerenteId;
    private int categoriaId;

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

    public int getId() {
        return id;
    }

    public String getTitulo() { 
        return titulo; 
    }

    public String getStatus() { 
        return status; 
    }

    public int getGerenteId() {
         return gerenteId;
    }

    public int getCategoriaId() {
         return categoriaId;
    }

    public void setId(int id) {
         this.id = id; 
    }

    public void setTitulo(String titulo) { 
        this.titulo = titulo; 
    }
    public void setStatus(String status) { 
        this.status = status; 
    }
    public void setGerenteId(int gerenteId) { 
        this.gerenteId = gerenteId; 
    }
    public void setCategoriaId(int categoriaId) { 
        this.categoriaId = categoriaId; 
    }
}


