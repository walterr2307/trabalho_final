import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tabuleiro {
    private int indice_casa, tipo_casa; // Variáveis para armazenar o índice da casa e o tipo de casa selecionados
    private ArrayList<Jogador> jogs; // Lista de jogadores
    private ArrayList<Casa> casas; // Lista de casas no tabuleiro
    private Scanner scanner; // Scanner para entrada de dados

    // Construtor da classe, inicializa jogadores e casas
    public Tabuleiro(ArrayList<String> nomes, ArrayList<String> cores, ArrayList<Integer> tipos, Scanner scanner) {
        this.scanner = scanner;
        this.jogs = definirJogadores(nomes, cores, tipos); // Define os jogadores com base nos nomes, cores e tipos
        this.casas = definirCasas(); // Define as casas do tabuleiro
    }

    // Método para definir os jogadores com base nos nomes, cores e tipos recebidos
    private ArrayList<Jogador> definirJogadores(ArrayList<String> nomes, ArrayList<String> cores,
            ArrayList<Integer> tipos) {
        ArrayList<Jogador> jogs = new ArrayList<Jogador>(); // Cria lista de jogadores

        // Laço para adicionar jogadores conforme o tipo (normal, sortudo ou azarado)
        for (int i = 0; i < nomes.size(); i++) {
            if (tipos.get(i) == 0)
                jogs.add(new JogadorNormal(nomes.get(i), cores.get(i), i, 0)); // Jogador normal
            else if (tipos.get(i) == 1)
                jogs.add(new JogadorSortudo(nomes.get(i), cores.get(i), i, 1)); // Jogador sortudo
            else
                jogs.add(new JogadorAzarado(nomes.get(i), cores.get(i), i, 2)); // Jogador azarado
        }

        return jogs;
    }

    // Método para capturar o número de casas especiais do usuário, com validação
    private int digitarNumCasasEspeciais() {
        int num_casas_especiais = 0;

        while (true) {
            try {
                System.out.print("\nInsira o numero de casas especiais: ");
                num_casas_especiais = scanner.nextInt();

                // Valida se o número está dentro do limite aceitável
                if (num_casas_especiais < 0 || num_casas_especiais > 39)
                    throw new AcaoInvalidaException(); // Lança exceção caso exceda o limite

                break;
            } catch (AcaoInvalidaException e) {
                e.excedeLimiteCasas(); // Exibe mensagem de erro para limite excedido
            }
        }

        return num_casas_especiais;
    }

    // Método para o usuário escolher o índice e o tipo da casa especial
    private void escolherIndiceCasa(ArrayList<Casa> casas) {

        while (true) {
            try {
                System.out.print("\nSelecione o indice da casa especial: ");
                indice_casa = scanner.nextInt() - 1; // Recebe o índice da casa
                System.out.print("Selecione o indice do tipo de casa: ");
                tipo_casa = scanner.nextInt() - 1; // Recebe o tipo de casa

                // Validações de limites para o índice e o tipo da casa
                if (indice_casa < 0 || indice_casa > 39 || tipo_casa < 0 || tipo_casa > 6
                        || casas.get(indice_casa) != null) {
                    throw new AcaoInvalidaException(); // Lança exceção caso o valor seja inválido
                }

                break;
            } catch (AcaoInvalidaException e) {
                // Verifica o tipo de erro e exibe a mensagem correspondente
                if (indice_casa < 0 || indice_casa > 39)
                    e.excedeLimiteCasas();
                else if (tipo_casa < 0 || tipo_casa > 6)
                    e.tipoCasaInvalido();
                else
                    e.casaJaSelecionada(); // Casa já foi selecionada anteriormente
            }
        }
    }

    // Método para criar a casa especial com base no tipo escolhido
    private void criarCasa(ArrayList<Casa> casas) {
        switch (tipo_casa) {
            case 0:
                casas.set(indice_casa, new CasaAzar()); // Cria CasaAzar
                break;
            case 1:
                casas.set(indice_casa, new CasaJogarDeNovo()); // Cria CasaJogarDeNovo
                break;
            case 2:
                casas.set(indice_casa, new CasaPrisao()); // Cria CasaPrisao
                break;
            case 3:
                casas.set(indice_casa, new CasaReversa()); // Cria CasaReversa
                break;
            case 4:
                casas.set(indice_casa, new CasaSorte()); // Cria CasaSorte
                break;
            case 5:
                casas.set(indice_casa, new CasaSurpresa()); // Cria CasaSurpresa
                break;
            default:
                casas.set(indice_casa, new CasaTroca()); // Cria CasaTroca para o valor padrão
                break;
        }
    }

    // Método para definir todas as casas do tabuleiro
    private ArrayList<Casa> definirCasas() {
        int num_casas_especiais = digitarNumCasasEspeciais(); // Recebe o número de casas especiais
        String tipo_casas[] = { "Azar", "Jogar de Novo", "Prisao", "Reversa", "Sorte", "Surpresa", "Troca" };

        casas = new ArrayList<Casa>(0); // Inicializa lista com 40 casas
        System.out.println();

        for (int i = 0; i < 40; i++)
            casas.add(null);

        // Exibe os tipos de casas especiais para o usuário escolher
        for (int i = 0; i < 7; i++)
            System.out.printf("%d.%s ", i + 1, tipo_casas[i]);

        // Laço para permitir a escolha das casas especiais pelo usuário
        for (int i = 0; i < num_casas_especiais; i++) {
            escolherIndiceCasa(casas); // Escolhe o índice e tipo da casa
            criarCasa(casas); // Cria a casa especial
        }

        // Preenche as casas restantes com CasaSimples
        for (int i = 0; i < 40; i++) {
            if (casas.get(i) == null)
                casas.set(i, new CasaSimples());
        }

        return casas;
    }

    private void iniciarPrimeiraRodada() {
        Casa casa = casas.get(0);

        System.out.println("\n");

        for (Jogador jog : jogs)
            casa.setSigla(jog.getSigla(), jog.getNumJogador());

    }

    private void imprimirTabuleiro() {
        for (int i = 0; i < 40; i++) {
            casas.get(i).imprimirCasa();

            if ((i + 1) % 8 == 0)
                System.out.println(); // Quebra de linha a cada 8 casas
        }

    }

    private void imprimirInfoJogs() {
        System.out.println();

        for (Jogador jog : jogs)
            jog.imprimirInfomacoes();
    }

    // Método para iniciar as rodadas do jogo
    public void iniciarRodadas() {
        int dados[] = new int[2], indice_jog = 0; // Inicializa variáveis para os dados e o índice do jogador
        Jogador jog = jogs.get(indice_jog); // Seleciona o primeiro jogador

        scanner.nextLine();

        iniciarPrimeiraRodada();
        imprimirTabuleiro();
        imprimirInfoJogs();

        System.out.print("\nDigite qualquer tecla: ");
        scanner.nextLine(); // Aguarda o jogador pressionar uma tecla

        // Loop principal do jogo
        while (true) {
            limparTerminal(); // Limpa o terminal
            System.out.println();

            casas.get(jog.getCasaAtual()).setSigla(' ', jog.getNumJogador());
            dados = jog.girarDados(); // Gira os dados
            jog.andarCasas(dados[0] + dados[1]); // Move o jogador pelo tabuleiro

            if (jog.getCasaAtual() == 40)
                break;

            casas.get(jog.getCasaAtual()).setSigla(jog.getSigla(), jog.getNumJogador()); // Atualiza a sigla da casa
                                                                                         // atual

            casas.get(jog.getCasaAtual()).aplicarRegra(jog, jogs);
            imprimirTabuleiro();
            imprimirInfoJogs();

            // Passa a vez para o próximo jogador
            indice_jog = (indice_jog + 1) % jogs.size();
            jog = jogs.get(indice_jog);

            System.out.print("\nDigite qualquer tecla: ");
            scanner.nextLine(); // Aguarda o jogador pressionar uma tecla
        }
    }

    // Método para limpar o terminal em diferentes sistemas operacionais
    private static void limparTerminal() {
        String os = System.getProperty("os.name").toLowerCase(); // Verifica o sistema operacional

        try {
            if (os.contains("windows")) {
                // Comando para limpar terminal no Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Comando para limpar terminal no Linux e MacOS
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace(); // Trata exceções de I/O ou interrupção
        }
    }
}
