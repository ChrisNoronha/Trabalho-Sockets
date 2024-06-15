package example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ServidorBiblioteca {
    private static final int PORT = 12345;
    private static final String FILE_PATH = "src/main/resources/livros.json";
    private List<Livro> livros;

    public ServidorBiblioteca() {
        this.livros = carregarLivros();
    }

    private List<Livro> carregarLivros() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            return new Gson().fromJson(reader, new TypeToken<List<Livro>>() {
            }.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void salvarLivros() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            new Gson().toJson(livros, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listarLivros(PrintWriter out) {
        for (Livro livro : livros) {
            out.println(livro.getNome() + " - " + livro.getAutor() + " - " + livro.getGenero() + " - " + livro.getNumeroDeExemplares() + " exemplares - " + livro.getNumeroDeAlugados() + " alugados");
        }
        out.println("FIM");
    }

    private void cadastrarLivro(String autor, String nome, String genero, int numeroDeExemplares) {
        livros.add(new Livro(autor, nome, genero, numeroDeExemplares));
        salvarLivros();
    }

    private void alugarLivro(String nome, PrintWriter out) {
        for (Livro livro : livros) {
            if (livro.getNome().equalsIgnoreCase(nome)) {
                if (livro.getNumeroDeExemplares() > 0) {
                    livro.setNumeroDeExemplares(livro.getNumeroDeExemplares() - 1);
                    livro.setNumeroDeAlugados(livro.getNumeroDeAlugados() + 1);
                    salvarLivros();
                    out.println("Livro alugado com sucesso.");
                } else {
                    out.println("Livro não disponível para aluguel.");
                }
                out.println("FIM");
                return;
            }
        }
        out.println("Livro não encontrado.");
        out.println("FIM");
    }

    private void devolverLivro(String nome, PrintWriter out) {
        for (Livro livro : livros) {
            if (livro.getNome().equalsIgnoreCase(nome)) {
                if (livro.getNumeroDeAlugados() > 0) {
                    livro.setNumeroDeExemplares(livro.getNumeroDeExemplares() + 1);
                    livro.setNumeroDeAlugados(livro.getNumeroDeAlugados() - 1);
                    salvarLivros();
                    out.println("Livro devolvido com sucesso.");
                } else {
                    out.println("Livro não está alugado.");
                }
                out.println("FIM");
                return;
            }
        }
        out.println("Livro não encontrado.");
        out.println("FIM");
    }
}


