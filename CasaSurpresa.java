import java.util.ArrayList;
import java.util.Scanner;

// Classe CasaSurpresa que herda de Casa. Aplica regras baseadas em sorte para mudar o tipo de jogador.
public class CasaSurpresa extends Casa {
    Scanner scanner = new Scanner(System.in);

    // Método privado que converte o tipo de jogador (int) em uma string
    // correspondente ("NORMAL", "SORTUDO" ou "AZARADO").
    private String atribuirTipoJogador(int tipo_jog) {
        String str_tipo = null;

        // Verifica o tipo do jogador e atribui a string correspondente.
        switch (tipo_jog) {
            case 0:
                str_tipo = "NORMAL";
                break;
            case 1:
                str_tipo = "SORTUDO";
                break;
            default:
                str_tipo = "AZARADO";
        }

        return str_tipo; // Retorna a string correspondente ao tipo de jogador.
    }

    // Aplica uma regra de mudança de tipo de jogador (NORMAL, SORTUDO ou AZARADO)
    // com base em uma escolha feita pelo jogador.
    public void aplicarRegra(Jogador jog, ArrayList<Jogador> jogs) {
        // Variáveis locais para armazenar informações temporárias.
        int j = 0, tipos_jog[] = new int[2], sorteio = (int) (Math.random() * 2), carta, indice = 0;
        int num_moedas, casa_atual;
        String str_tipos[] = new String[2], str_carta, nome, cor;

        // Loop que define os dois tipos de jogadores possíveis, exceto o tipo atual do
        // jogador.
        for (int i = 0; i < 3; i++) {
            if (i != jog.getTipoJogador()) {
                tipos_jog[j] = i;
                ++j; // Incrementa o índice do array tipos_jog.
            } else {
                indice = i; // Armazena o tipo atual do jogador.
            }
        }

        // Sorteia qual dos dois tipos possíveis será apresentado primeiro.
        if (sorteio == 1) {
            sorteio = tipos_jog[0];
            tipos_jog[0] = tipos_jog[1];
            tipos_jog[1] = sorteio;
        }

        // Converte os tipos inteiros para suas representações em string.
        str_tipos[0] = atribuirTipoJogador(tipos_jog[0]);
        str_tipos[1] = atribuirTipoJogador(tipos_jog[1]);

        // Loop que solicita ao jogador a escolha de uma carta, garantindo que a escolha
        // seja válida.
        while (true) {
            try {
                System.out.printf("\n1.%s  2.%s\n", str_tipos[0], str_tipos[1]);
                System.out.print("Selecione um tipo de carta: ");
                carta = scanner.nextInt() - 1; // Subtrai 1 para ajustar o índice ao array.

                // Lança exceção se a escolha for inválida.
                if (carta < 0 || carta > 1)
                    throw new AcaoInvalidaException();

                str_carta = str_tipos[carta]; // Armazena o tipo de carta selecionado.

                break; // Sai do loop quando a seleção for válida.
            } catch (AcaoInvalidaException e) {
                e.tipoNaoExistente(); // Trata exceções de ações inválidas.
            }
        }

        // Armazena as informações do jogador antes de alterar o tipo.
        nome = jog.getNome();
        cor = jog.getCor();
        num_moedas = jog.getNumMoedas();
        casa_atual = jog.getCasaAtual();

        // Verifica o tipo de carta selecionado e altera o tipo do jogador na lista de
        // jogadores.
        switch (str_carta) {
            case "NORMAL":
                jogs.set(indice, new JogadorNormal(nome, cor, indice, 0));
                jogs.get(indice).atualizarDados(num_moedas, casa_atual);
                break;
            case "SORTUDO":
                jogs.set(indice, new JogadorSortudo(nome, cor, indice, 1));
                jogs.get(indice).atualizarDados(num_moedas, casa_atual);
                break;
            default:
                jogs.set(indice, new JogadorAzarado(nome, cor, indice, 2));
                jogs.get(indice).atualizarDados(num_moedas, casa_atual);
        }

        System.out.println(); // Imprime uma linha em branco ao final do método.
    }

    public String getMsg(Jogador jog) {
        return "Trocou de tipo!";
    }
}
