package example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
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
            return new Gson().fromJson(reader, new TypeToken<List<Livro>>(){}.getType());
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

    public void iniciar() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado na porta " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String request;
                while ((request = in.readLine()) != null) {
                    String[] parts = request.split(";");
                    String action = parts[0];

                    switch (action) {
                        case "LISTAR":
                            listarLivros(out);
                            break;
                        case "CADASTRAR":
                            if (parts.length == 5) {
                                String autor = parts[1];
                                String nome = parts[2];
                                String genero = parts[3];
                                int numeroDeExemplares = Integer.parseInt(parts[4]);
                                cadastrarLivro(autor, nome, genero, numeroDeExemplares);
                                out.println("Livro cadastrado com sucesso.");
                                out.println("FIM");
                            } else {
                                out.println("Dados insuficientes para cadastro.");
                                out.println("FIM");
                            }
                            break;
                        case "ALUGAR":
                            if (parts.length == 2) {
                                alugarLivro(parts[1], out);
                            } else {
                                out.println("Nome do livro não informado.");
                                out.println("FIM");
                            }
                            break;
                        case "DEVOLVER":
                            if (parts.length == 2) {
                                devolverLivro(parts[1], out);
                            } else {
                                out.println("Nome do livro não informado.");
                                out.println("FIM");
                            }
                            break;
                        default:
                            out.println("Ação desconhecida.");
                            out.println("FIM");
                            break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ServidorBiblioteca().iniciar();
    }
}
