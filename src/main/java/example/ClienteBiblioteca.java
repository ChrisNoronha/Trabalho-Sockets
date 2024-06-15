package example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClienteBiblioteca {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectado ao servidor da biblioteca.");

            while (true) {
                System.out.println("Escolha uma ação: LISTAR, CADASTRAR, ALUGAR, DEVOLVER ou SAIR:");
                String action = scanner.nextLine().toUpperCase();

                if (action.equals("SAIR")) {
                    break;
                }

                switch (action) {
                    case "LISTAR":
                        out.println("LISTAR;");
                        break;
                    case "CADASTRAR":
                        System.out.println("Digite o autor:");
                        String autor = scanner.nextLine();
                        System.out.println("Digite o nome:");
                        String nome = scanner.nextLine();
                        System.out.println("Digite o gênero:");
                        String genero = scanner.nextLine();
                        System.out.println("Digite o número de exemplares:");
                        int numeroDeExemplares = Integer.parseInt(scanner.nextLine());
                        out.println("CADASTRAR;" + autor + ";" + nome + ";" + genero + ";" + numeroDeExemplares);
                        break;
                    case "ALUGAR":
                        System.out.println("Digite o nome do livro para alugar:");
                        String nomeAlugar = scanner.nextLine();
                        out.println("ALUGAR;" + nomeAlugar);
                        break;
                    case "DEVOLVER":
                        System.out.println("Digite o nome do livro para devolver:");
                        String nomeDevolver = scanner.nextLine();
                        out.println("DEVOLVER;" + nomeDevolver);
                        break;
                    default:
                        System.out.println("Ação desconhecida.");
                }

                // Leitura da resposta do servidor
                String response;
                StringBuilder serverResponse = new StringBuilder();
                while ((response = in.readLine()) != null && !response.equals("FIM")) {
                    serverResponse.append(response).append("\n");
                }
                System.out.println(serverResponse.toString().trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
