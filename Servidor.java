import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket servidor = new ServerSocket(12345);
        System.out.println("Servidor conectado a porta 12345!");

        while (true) {
            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado: " + cliente.getInetAddress().getHostAddress());

            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintStream saida = new PrintStream(cliente.getOutputStream());

            String mensagem = entrada.readLine();
            System.out.println("Mensagem recebida: " + mensagem);

            if (mensagem.startsWith("SIGNO:")) {
                String[] partes = mensagem.split(":")[1].split("/");
                int dia = Integer.parseInt(partes[0]);
                int mes = Integer.parseInt(partes[1]);

                String signo = descobrirSigno(dia, mes);
                saida.println("Seu signo é: " + signo);
            } else if (mensagem.equals("HOROSCOPO")) {
                saida.println("Hoje é um ótimo dia para aprender algo novo!");
            } else {
                saida.println("Comando inválido.");
            }

            entrada.close();
            saida.close();
            cliente.close();
        }
    }

    private static String descobrirSigno(int dia, int mes) {
        if ((mes == 3 && dia >= 21) || (mes == 4 && dia <= 19))
            return "Áries";
        if ((mes == 4 && dia >= 20) || (mes == 5 && dia <= 20))
            return "Touro";
        if ((mes == 5 && dia >= 21) || (mes == 6 && dia <= 20))
            return "Gêmeos";
        if ((mes == 6 && dia >= 21) || (mes == 7 && dia <= 22))
            return "Câncer";
        if ((mes == 7 && dia >= 23) || (mes == 8 && dia <= 22))
            return "Leão";
        if ((mes == 8 && dia >= 23) || (mes == 9 && dia <= 22))
            return "Virgem";
        if ((mes == 9 && dia >= 23) || (mes == 10 && dia <= 22))
            return "Libra";
        if ((mes == 10 && dia >= 23) || (mes == 11 && dia <= 21))
            return "Escorpião";
        if ((mes == 11 && dia >= 22) || (mes == 12 && dia <= 21))
            return "Sagitário";
        if ((mes == 12 && dia >= 22) || (mes == 1 && dia <= 19))
            return "Capricórnio";
        if ((mes == 1 && dia >= 20) || (mes == 2 && dia <= 18))
            return "Aquário";
        if ((mes == 2 && dia >= 19) || (mes == 3 && dia <= 20))
            return "Peixes";
        return "Desconhecido";
    }
}
