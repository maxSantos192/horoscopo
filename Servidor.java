import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Servidor {
    public static void main(String[] args) throws IOException {
        int porta = 12345;
        ServerSocket servidor = new ServerSocket(porta);
        System.out.println("Servidor de Signos iniciado na porta " + porta);

        while (true) {
            Socket cliente = servidor.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    tratarCliente(cliente);
                }
            }).start();
        }
    }

    public static void tratarCliente(Socket socket) {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
            saida.println("Bem-vindo! \nDigite 1 - para descobrir o signo \n2 - para ver o horóscopo do dia");
            String opcao = entrada.readLine();

            if ("1".equals(opcao)) {
                saida.println("Digite sua data de nascimento no formato dd/MM:");
                String dataNascimento = entrada.readLine();
                String signo = descobrirSigno(dataNascimento);
                saida.println("Seu signo e: " + signo);
            } else if ("2".equals(opcao)) {
                saida.println("Digite seu signo (ex: Aries, Touro, etc.):");
                String signo = entrada.readLine().toLowerCase();
                String horoscopo = getHoroscopo(signo);
                saida.println("Horoscopo para " + signo + ": " + horoscopo);
            } else {
                saida.println("Opção inválida.");
            }

        } catch (IOException e) {
            System.out.println("Erro ao comunicar com cliente: " + e.getMessage());
        }
    }

    public static String descobrirSigno(String dataStr) {
        try {
            LocalDate data = LocalDate.parse(dataStr + "/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            int dia = data.getDayOfMonth();
            int mes = data.getMonthValue();

            switch (mes) {
                case 1:
                    return (dia < 20) ? "Capricórnio" : "Aquário";
                case 2:
                    return (dia < 19) ? "Aquário" : "Peixes";
                case 3:
                    return (dia < 21) ? "Peixes" : "Áries";
                case 4:
                    return (dia < 20) ? "Áries" : "Touro";
                case 5:
                    return (dia < 21) ? "Touro" : "Gêmeos";
                case 6:
                    return (dia < 21) ? "Gêmeos" : "Câncer";
                case 7:
                    return (dia < 23) ? "Câncer" : "Leão";
                case 8:
                    return (dia < 23) ? "Leão" : "Virgem";
                case 9:
                    return (dia < 23) ? "Virgem" : "Libra";
                case 10:
                    return (dia < 23) ? "Libra" : "Escorpião";
                case 11:
                    return (dia < 22) ? "Escorpião" : "Sagitário";
                case 12:
                    return (dia < 22) ? "Sagitário" : "Capricórnio";
                default:
                    return "Data inválida";
            }
        } catch (Exception e) {
            return "Erro ao interpretar a data.";
        }
    }

    public static String getHoroscopo(String signo) {
        switch (signo) {
            case "aries":
                return "Hoje é um bom dia para começar algo novo.";
            case "touro":
                return "Evite gastos desnecessários e confie no seu instinto.";
            case "gemeos":
                return "Comunique-se com clareza para evitar mal-entendidos.";
            case "cancer":
                return "Cuide das suas emoções e ouça seu coração.";
            case "leao":
                return "Brilhe: return mas lembre-se de ouvir os outros.";
            case "virgem":
                return "Organize suas tarefas e mantenha o foco.";
            case "libra":
                return "Busque o equilíbrio nas suas decisões.";
            case "escorpiao":
                return "A profundidade dos sentimentos trará revelações.";
            case "sagitario":
                return "Aventure-se: return mas não negligencie suas responsabilidades.";
            case "capricornio":
                return "Trabalho duro trará recompensas.";
            case "aquario":
                return "Ideias inovadoras estarão em destaque.";
            case "peixes":
                return "Sua intuição será seu melhor guia hoje.";
            default:
                return "Nao achei seu signo";
        }
    }
}
