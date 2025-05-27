package models;

public class Gerente {
    private int id;
    private String nome;
    private String email;
    private String pin;

    // Construtor
    public Gerente(String nome, String email, String pin) {
        this.nome = nome;
        this.email = email;
        this.pin = pin;
    }

    public Gerente(int id, String nome, String email, String pin) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.pin = pin;
    }

    // Getters e setters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getpin() { return pin; }

    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setpin(String pin) { this.pin = pin; }
}
