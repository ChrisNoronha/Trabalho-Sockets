package example;

import java.io.Serializable;

public class Livro implements Serializable {
    private String autor;
    private String nome;
    private String genero;
    private int numeroDeExemplares;
    private int numeroDeAlugados;

    public Livro(String autor, String nome, String genero, int numeroDeExemplares) {
        this.autor = autor;
        this.nome = nome;
        this.genero = genero;
        this.numeroDeExemplares = numeroDeExemplares;
        this.numeroDeAlugados = 0; // Inicializa como 0
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getNumeroDeExemplares() {
        return numeroDeExemplares;
    }

    public void setNumeroDeExemplares(int numeroDeExemplares) {
        this.numeroDeExemplares = numeroDeExemplares;
    }

    public int getNumeroDeAlugados() {
        return numeroDeAlugados;
    }

    public void setNumeroDeAlugados(int numeroDeAlugados) {
        this.numeroDeAlugados = numeroDeAlugados;
    }
}
