import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Tabuleiro {
    private static Tabuleiro instancia; // Instância única do Tabuleiro
    private int indice_jog = 0, dados[] = { 0, 0 }; // Índice do jogador atual e array para armazenar os dados
    private boolean modo_debug, seg_vez_bloqueadas[] = { false, false, false, false, false, false };
    private String msg = "O jogo comecou!"; // Mensagem inicial do jogo
    private Jogador jog; // Jogador atual
    private Scanner scanner = new Scanner(System.in); // Scanner para entrada do usuário
    private ArrayList<Casa> casas = new ArrayList<Casa>(); // Lista de casas do tabuleiro
    private ArrayList<Jogador> jogs = new ArrayList<Jogador>(); // Lista de jogadores
    private AuxiliarTabuleiro aux_tabuleiro = new AuxiliarTabuleiro(casas, jogs); // Objeto auxiliar para configurar o
                                                                                  // tabuleiro e jogadores

    // Construtor privado para impedir a criação de novas instâncias
    private Tabuleiro() {
    }

    // Método estático para obter a instância única do Tabuleiro (Padrão Singleton)
    public static Tabuleiro instanciar() {
        if (instancia == null) {
            instancia = new Tabuleiro(); // Cria uma nova instância se não houver nenhuma
        }
        return instancia;
    }

    // Configura a quantidade de casas no tabuleiro
    public void configurarCasas(int qtd_casas) {
        aux_tabuleiro.configurarCasas(qtd_casas); // Chama método auxiliar para configurar as casas
    }

    // Configura a quantidade de jogadores
    public void configurarJogadores(int qtd_jogs) {
        aux_tabuleiro.configurarJogadores(qtd_jogs); // Chama método auxiliar para configurar os jogadores
    }

    // Define o modo debug
    public void setModoDebug(boolean modo_debug) {
        this.modo_debug = modo_debug; // Define o valor do modo debug
    }

    // Ajusta o primeiro jogador da partida e inicializa as casas para todos os
    // jogadores
    public void ajustarPrimeiroJogador(int qtd_casas) {
        for (Jogador jogador : jogs) // Para cada jogador na lista
            jogador.setQtdCasas(qtd_casas); // Define a quantidade de casas no tabuleiro

        jog = jogs.get(indice_jog); // Define o primeiro jogador
        jog.setMinhaVez(true); // Sinaliza que é a vez do primeiro jogador
    }

    // Verifica se o jogador deve passar a vez para outro
    private void verificarCondicoesAtualizar() {
        if (!jog.getJogarNovamente()) { // Se o jogador não deve jogar novamente
            if (modo_debug)
                atualizarJogador(); // Atualiza o jogador automaticamente no modo debug
            else if (dados[0] != dados[1]) // Se os dados não têm valores iguais
                atualizarJogador(); // Passa a vez para o próximo jogador
        }
    }

    // Método para manipular o jogo no modo debug, permitindo alterar a posição do
    // jogador
    private void debugar() {
        int pos = 0;

        while (true) {
            try {
                System.out.print("\nSelecione a nova posicao: ");
                pos = scanner.nextInt(); // Recebe a nova posição do jogador
                break; // Sai do loop se a entrada for válida
            } catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número inteiro válido."); // Exibe erro se a entrada for
                                                                                   // inválida
                scanner.next(); // Limpa a entrada inválida
            }
        }

        scanner.nextLine(); // Limpa o buffer
        jog.setCasaAtual(pos); // Define a nova posição do jogador
    }

    // Método que inicia uma rodada para o jogador atual
    public void comecarRodada() {
        System.out.printf("Mensagem: %s\n", msg); // Exibe mensagem de estado do jogo
        jog.addNumJogadas(); // Incrementa o número de jogadas para o jogador

        if (!modo_debug) // Se não estiver no modo debug
            System.out.printf("Valor dos dados: [%d][%d]\n\n", dados[0], dados[1]); // Exibe o valor dos dados
        else
            System.out.println(); // No modo debug, exibe uma linha vazia

        // Atualiza e exibe as informações de todos os jogadores
        for (Jogador jogador : jogs) {
            jogador.atualizarInfos();
            System.out.println(jogador.imprimirInformacoes());
        }

        if (modo_debug) {
            debugar(); // Permite alterar a posição do jogador no modo debug
        } else {
            System.out.print("\nPressione ENTER para girar os dados: ");
            scanner.nextLine(); // Aguarda o jogador pressionar ENTER

            dados = jog.girarDados(); // Gira os dados e armazena os valores
            jog.andarCasas(dados[0] + dados[1]); // Move o jogador pela soma dos dados
        }

        msg = casas.get(jog.getCasaAtual()).getMsg(jog); // Define a mensagem da casa atual do jogador
        casas.get(jog.getCasaAtual()).aplicarRegra(indice_jog, jog, jogs); // Aplica as regras da casa atual
    }

    // Método que continua a execução das rodadas
    public void continuarRodadas() {
        boolean fugiu_prisao;

        verificarCondicoesAtualizar(); // Verifica se o jogador atual deve ser atualizado

        while (true) {
            limparTerminal(); // Limpa o terminal

            if (jog.getVezBloqueada()) { // Se a vez do jogador estiver bloqueada
                jog.setVezBloqueada(false); // Desbloqueia a vez do jogador
                seg_vez_bloqueadas[indice_jog] = true; // Marca que foi a segunda vez bloqueada

                System.out.print("\tJogada bloqueada!\n\nPressione ENTER para continuar: ");
                scanner.nextLine(); // Aguarda o jogador pressionar ENTER

                atualizarJogador(); // Passa a vez para o próximo jogador
            } else {

                if (seg_vez_bloqueadas[indice_jog]) { // Se foi a segunda vez bloqueada
                    fugiu_prisao = fugirPrisao(); // Tenta fugir da prisão

                    if (!fugiu_prisao) {
                        atualizarJogador(); // Atualiza para o próximo jogador se não fugiu da prisão
                        continue;
                    }
                }

                comecarRodada(); // Inicia a rodada do jogador atual

                if (jog.getCasaAtual() >= casas.size() - 1) // Verifica se o jogador chegou ao fim do tabuleiro
                    break;

                verificarCondicoesAtualizar(); // Verifica se o jogador atual deve ser atualizado

                if (jog.getJogarNovamente()) // Se o jogador tem o direito de jogar novamente
                    jog.setJogarNovamente(false); // Reseta a flag de jogar novamente
            }
        }

        finalizarJogo(); // Finaliza o jogo
    }

    // Método para finalizar o jogo, exibindo o vencedor e as estatísticas dos
    // jogadores
    private void finalizarJogo() {
        limparTerminal(); // Limpa o terminal
        System.out.printf("\tJogador %s venceu!!!\n", jog.getCor()); // Exibe o jogador vencedor

        // Exibe o número de rodadas para cada jogador
        for (Jogador jogador : jogs) {
            System.out.printf("\nJogador %s %s: %d rodadas", jogador.getTipo(), jogador.getCor(),
                    jogador.getNumJogadas());
        }
    }

    // Método para passar a vez para o próximo jogador
    private void atualizarJogador() {
        indice_jog = (indice_jog + 1) % jogs.size(); // Atualiza o índice do jogador atual
        jog.setMinhaVez(false); // Define que não é mais a vez do jogador anterior
        jog = jogs.get(indice_jog); // Passa a vez para o próximo jogador
        jog.setMinhaVez(true); // Define que é a vez do novo jogador
    }

    // Método que lida com a tentativa de fuga da prisão
    private boolean fugirPrisao() {
        boolean fugiu_prisao = false;

        seg_vez_bloqueadas[indice_jog] = false; // Reseta a flag de segunda vez bloqueada

        System.out.println("\tSegunda jogada bloqueada!");

        // Laço para perguntar ao jogador se ele quer gastar moedas para fugir da prisão
        while (true) {
            System.out.print("\nDeseja gastar duas moedas para fugir da prisao? (true ou false): ");
            String input = scanner.nextLine().toLowerCase(); // Lê a resposta do usuário e converte para minúsculo

            if (input.equals("true") || input.equals("false")) {
                fugiu_prisao = Boolean.parseBoolean(input); // Converte a string em booleano
                break; // Sai do loop se a entrada for válida
            } else {
                System.out.println("Por favor, digite 'true' ou 'false'."); // Pede uma entrada válida
            }
        }

        if (!fugiu_prisao)
            return false; // Retorna falso se o jogador decidiu não fugir

        try {
            if (jog.getQtdMoedas() < 2) // Verifica se o jogador tem moedas suficientes
                throw new AcaoInvalidaException(); // Lança exceção se não houver moedas suficientes

            jog.addMoedas(-2); // Remove duas moedas do jogador
        } catch (AcaoInvalidaException e) {
            e.moedasInsuficientes(); // Exibe mensagem de erro

            System.out.print("Pressione ENTER para continuar: ");
            scanner.nextLine(); // Aguarda o jogador pressionar ENTER

            return false; // Retorna falso se não conseguiu fugir
        }

        return true; // Retorna verdadeiro se conseguiu fugir
    }

    // Método para limpar o terminal, dependendo do sistema operacional
    private void limparTerminal() {
        try {
            String os = System.getProperty("os.name").toLowerCase(); // Detecta o sistema operacional

            // Verifica se é Windows
            if (os.contains("win")) {
                // Executa o comando cls no Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Executa o comando clear em sistemas Unix (Linux/Mac)
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            // Caso ocorra uma exceção, imprime o stack trace
            e.printStackTrace();
        }
    }
}
