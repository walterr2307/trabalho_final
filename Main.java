import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int qtd_jogs = 0, qtd_casas = 0; // Variáveis para armazenar quantidade de jogadores e casas
        boolean modo_debug = false; // Variável para armazenar o estado do modo debug
        Jogo jogo = new Jogo(); // Instanciação da classe Jogo
        Scanner scanner = new Scanner(System.in); // Objeto Scanner para leitura de entradas do usuário

        // Loop para perguntar se o usuário quer ativar o modo debug
        while (true) {
            System.out.print("Quer ativar o modo debug? (true/false): ");
            String input = scanner.nextLine().toLowerCase(); // Lê a resposta do usuário e converte para minúsculo

            if (input.equals("true") || input.equals("false")) {
                modo_debug = Boolean.parseBoolean(input); // Converte a string em booleano
                break; // Sai do loop se a entrada for válida
            } else {
                System.out.println("Por favor, digite 'true' ou 'false'."); // Pede uma entrada válida
            }
        }

        // Loop para ler e validar a quantidade de casas
        while (true) {
            try {
                // Loop interno para capturar erros de entrada do usuário
                while (true) {
                    try {
                        System.out.print("Digite a quantidade de casas: ");
                        qtd_casas = scanner.nextInt(); // Lê a quantidade de casas
                        break; // Sai do loop se a entrada for válida
                    } catch (InputMismatchException e) {
                        System.out.println("Por favor, digite um número inteiro válido."); // Exibe erro se a entrada
                                                                                           // for inválida
                        scanner.next(); // Limpa a entrada inválida
                    }
                }

                // Loop para ler e validar a quantidade de jogadores
                while (true) {
                    try {
                        System.out.print("Digite a quantidade de jogadores: ");
                        qtd_jogs = scanner.nextInt(); // Lê a quantidade de jogadores
                        break; // Sai do loop se a entrada for válida
                    } catch (InputMismatchException e) {
                        System.out.println("Por favor, digite um número inteiro válido."); // Exibe erro se a entrada
                                                                                           // for inválida
                        scanner.next(); // Limpa a entrada inválida
                    }
                }

                // Verifica se a quantidade de casas ou jogadores é inválida
                if (qtd_casas < 10 || qtd_jogs < 2 || qtd_jogs > 6)
                    throw new AcaoInvalidaException(); // Lança exceção se os valores forem inválidos

                break; // Sai do loop principal se as entradas forem válidas
            } catch (AcaoInvalidaException e) {
                if (qtd_casas < 10)
                    e.qtdCasasInsuficientes(); // Exibe erro se a quantidade de casas for menor que o mínimo
                else
                    e.qtdJogsInvalida(); // Exibe erro se a quantidade de jogadores for inválida
            }
        }

        // Configura o tabuleiro com a quantidade de casas e jogadores
        jogo.configTabuleiro(qtd_casas); // Configura o tabuleiro com base na quantidade de casas
        jogo.config(qtd_jogs); // Configura os jogadores
        jogo.printTabuleiro(qtd_casas, modo_debug); // Imprime o estado inicial do tabuleiro e jogadores
        jogo.start(); // Inicia o jogo

        scanner.close(); // Fecha o scanner
    }
}
