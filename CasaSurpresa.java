import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CasaSurpresa extends Casa {
    private Scanner scanner = new Scanner(System.in); // Scanner para ler a entrada do jogador
    private Fabrica fabrica = new Fabrica(); // Fabrica usada para criar um novo jogador do tipo selecionado

    // Método que aplica a regra da Casa Surpresa
    // Permite ao jogador escolher entre duas cartas e, com base na escolha, troca o
    // tipo do jogador
    public void aplicarRegra(int indice, Jogador jog, ArrayList<Jogador> jogs) {
        int carta_escolhida, sortear = (int) (Math.random() * 2); // Sorteio para decidir se inverte os tipos
        int casa_atual, qtd_moedas, qtd_casas, num_jogadas; // Variáveis para armazenar o estado atual do jogador
        String copia, tipo[] = new String[2]; // Array para armazenar os tipos de jogador possíveis

        // Define os dois tipos alternativos com base no tipo atual do jogador
        if (jog.getTipo().equals("normal")) {
            tipo[0] = "sortudo";
            tipo[1] = "azarado";
        } else if (jog.getTipo().equals("sortudo")) {
            tipo[0] = "normal";
            tipo[1] = "azarado";
        } else {
            tipo[0] = "normal";
            tipo[1] = "sortudo";
        }

        // Sorteio para decidir se os tipos devem ser invertidos
        if (sortear == 1) {
            copia = tipo[0]; // Troca os valores de tipo[0] e tipo[1] se o sorteio for 1
            tipo[0] = tipo[1];
            tipo[1] = copia;
        }

        // Loop para garantir que a entrada do jogador seja válida
        while (true) {
            try {
                while (true) {
                    try {
                        System.out.print("\n\nEscolha uma carta(1 ou 2): ");
                        carta_escolhida = scanner.nextInt(); // Lê a escolha da carta
                        break; // Sai do loop se a entrada for válida
                    } catch (InputMismatchException e) {
                        // Trata a exceção se o jogador não digitar um número inteiro
                        System.out.println("Por favor, digite um número inteiro válido.");
                        scanner.next(); // Limpa a entrada inválida
                    }
                }

                if (carta_escolhida < 1 || carta_escolhida > 2) // Verifica se a carta escolhida está fora do intervalo
                                                                // permitido
                    throw new AcaoInvalidaException(); // Lança exceção customizada se a escolha for inválida

                scanner.nextLine(); // Limpa o buffer de entrada
                break;
            } catch (AcaoInvalidaException e) {
                e.cartaInvalida(); // Mostra uma mensagem de erro se a carta escolhida for inválida
            }
        }

        // Salva o estado atual do jogador antes de mudar o tipo
        qtd_moedas = jog.getQtdMoedas();
        qtd_casas = jog.getQtdCasas();
        casa_atual = jog.getCasaAtual();
        num_jogadas = jog.getNumJogadas();

        // Cria um novo jogador com o tipo selecionado pela carta escolhida
        jog = fabrica.retornarJogador(tipo[carta_escolhida - 1], jog.getCor());

        // Restaura o estado anterior do jogador (moedas, posição, etc.) após a troca de
        // tipo
        jog.setCasaAtual(casa_atual);
        jog.setQtdCasas(qtd_casas);
        jog.setQtdMoedas(qtd_moedas);
        jog.setNumJogadas(num_jogadas);

        // Atualiza a lista de jogadores com o novo tipo do jogador
        jogs.set(indice, jog);
    }

    // Método que retorna uma mensagem indicando que o jogador trocou de tipo
    public String getMsg(Jogador jog) {
        return "Trocou de tipo!"; // Mensagem informando que o tipo do jogador foi alterado
    }
}
