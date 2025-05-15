import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 12345);
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);

            System.out.println(entrada.readLine()); // Bem-vindo
            String opcao = teclado.readLine();
            saida.println(opcao);

            System.out.println(entrada.readLine()); // Pergunta
            String resposta1 = teclado.readLine();
            saida.println(resposta1);

            System.out.println(entrada.readLine()); // Resposta do servidor
        } finally {
            socket.close();
        }
    }
}
