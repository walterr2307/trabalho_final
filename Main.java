import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int qtd_jogs = 0, qtd_casas = 0;
        Jogo jogo = new Jogo();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Digite a quantidade de casas: ");
                qtd_casas = scanner.nextInt();
                System.out.print("Digite a quantidade de jogadores: ");
                qtd_jogs = scanner.nextInt();

                if (qtd_casas < 3 || qtd_jogs < 2 || qtd_jogs > 6)
                    throw new AcaoInvalidaException();

                break;
            } catch (AcaoInvalidaException e) {
                if (qtd_casas < 3)
                    e.qtdCasasInsuficientes();
                else
                    e.QtdJogsInvalida();
            }
        }

        jogo.configTabuleiro(qtd_casas);
        jogo.config(qtd_jogs);
        jogo.printTabuleiro();
        jogo.start();

        scanner.close();
    }
}