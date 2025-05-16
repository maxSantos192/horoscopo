import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) throws IOException {
        Socket cliente = new Socket("127.0.0.1", 12345);
        PrintStream saida = new PrintStream(cliente.getOutputStream());
        BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
        Scanner scanner = new Scanner(System.in);

        System.out.println(" *** Os astros não mentem. Descubra o que o destino preparou hoje! *** ");
        System.out.println(" *** Bem-vindo ***");
        System.out.println("Digite 1 - Para descobrir o signo");
        System.out.println("Digite 2 - Para ver o horóscopo do dia");
        System.out.print("Escolha: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao == 1) {
            System.out.print("Digite o dia de nascimento: ");
            int dia = scanner.nextInt();
            System.out.print("Digite o mês de nascimento: ");
            int mes = scanner.nextInt();
            scanner.nextLine();

            String comando = "SIGNO:" + dia + "/" + mes;
            saida.println(comando);
        } else if (opcao == 2) {
            saida.println("HOROSCOPO");
        } else {
            System.out.println("Opção inválida.");
            saida.println("INVALIDO");
        }

        String resposta = entrada.readLine();
        System.out.println("Servidor respondeu:\n" + resposta);

        entrada.close();
        saida.close();
        cliente.close();
        scanner.close();
    }
}
