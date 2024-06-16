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
                System.out.println("Escolha uma ação:");
                System.out.println("1 - Listar");
                System.out.println("2 - Cadastrar");
                System.out.println("3 - Alugar");
                System.out.println("4 - Devolver");
                System.out.println("5 - Sair");
                System.out.println("Você pode digitar o número ou o nome da ação.");

                String action = scanner.nextLine().toUpperCase();

                switch (action) {
                    case "1":
                    case "LISTAR":
                        out.println("LISTAR;");
                        break;
                    case "2":
                    case "CADASTRAR":
                        System.out.println("Digite o autor do livro:");
                        String autor = scanner.nextLine();
                        System.out.println("Digite o nome do livro:");
                        String nome = scanner.nextLine();
                        System.out.println("Digite o gênero:");
                        String genero = scanner.nextLine();

                        int numeroDeExemplares;
                        while (true) {
                            System.out.println("Digite o número de exemplares:");
                            String input = scanner.nextLine();
                            try {
                                numeroDeExemplares = Integer.parseInt(input);
                                if(numeroDeExemplares < 0) {
                                    System.out.println("Por favor, digite apenas um número inteiro.");

                                }
                                if(numeroDeExemplares >= 0) {
                                    break;

                                }

                            } catch (NumberFormatException e) {
                                System.out.println("Por favor, digite apenas um número inteiro.");
                            }
                        }

                        out.println("CADASTRAR;" + autor + ";" + nome + ";" + genero + ";" + numeroDeExemplares);
                        break;
                    case "3":
                    case "ALUGAR":
                        System.out.println("Digite o ID do livro para alugar:");
                        String idAlugar = scanner.nextLine();
                        while (!idAlugar.matches("\\d+")) {
                            System.out.println("Digite o ID correto:");
                            idAlugar = scanner.nextLine();
                        }
                        out.println("ALUGAR;" + idAlugar);


                        String responseAlugar = in.readLine();
                        if (responseAlugar.startsWith("Livro")) {
                            System.out.println(responseAlugar);
                        } else {
                            System.out.println("Falha ao alugar o livro.");
                        }
                        break;
                    case "4":
                    case "DEVOLVER":
                        System.out.println("Digite o ID do livro para devolver:");
                        String idDevolver = scanner.nextLine();
                        while (!idDevolver.matches("\\d+")) {
                            System.out.println("Digite o ID correto:");
                            idDevolver = scanner.nextLine();
                        }
                        out.println("DEVOLVER;" + idDevolver);
                        break;
                    case "5":
                    case "SAIR":
                        return;
                    default:
                        System.out.println("Ação desconhecida.");
                        continue;
                }

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
