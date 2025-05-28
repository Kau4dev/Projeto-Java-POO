package models;

public class Categoria {
    private int id;
    private String titulo;

    public Categoria(String titulo) {
        this.titulo = titulo;
    }

    public Categoria(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public int getId() {
         return id; 
    }
    public String getTitulo() {
         return titulo; 
    }

    public void setId(int id) {
         this.id = id; 
    }
    public void setTitulo(String titulo) {
         this.titulo = titulo;
    }
}

