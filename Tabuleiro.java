import java.util.ArrayList;
import java.util.Scanner;

public class Tabuleiro {
    // A única instância da classe Tabuleiro (Singleton)
    private static Tabuleiro instancia;
    private int[] dados;
    private Jogador jog;
    private Scanner scanner = new Scanner(System.in);
    private Factory factory = new Factory();
    private ArrayList<Jogador> jogs = new ArrayList<Jogador>();
    private ArrayList<Casa> casas = new ArrayList<Casa>();

    // Construtor privado para evitar que outras classes criem uma nova instância
    private Tabuleiro() {
    }

    // Método estático que retorna a única instância da classe (implementando o
    // padrão Singleton)
    public static Tabuleiro instanciar() {
        if (instancia == null) {
            // Se a instância ainda não foi criada, ela é instanciada
            instancia = new Tabuleiro();
        }
        return instancia;
    }

    // Verifica se o tipo de casa informado é válido
    private boolean tipoCasaValido(String tipo_casa) {
        boolean tipo_valido = false;

        // Array com os tipos de casas válidos
        String tipos_casas[] = { "AZAR", "JOGAR DE NOVO", "PRISAO", "REVERSA", "SORTE", "SURPRESA", "TROCA" };

        // Verifica se o tipo de casa informado existe no array de tipos válidos
        for (int i = 0; i < 7; i++) {
            if (tipo_casa.equals(tipos_casas[i])) {
                tipo_valido = true;
                break;
            }
        }

        return tipo_valido;
    }

    // Método para ajustar as casas especiais do tabuleiro
    private void ajustarCasasEspeciais(int qtd_casas_especiais, int qtd_casas) {
        int i = 0, pos = 0;
        String tipo_casa;
        String tipos_casas[] = { "AZAR", "JOGAR DE NOVO", "PRISAO", "REVERSA", "SORTE", "SURPRESA", "TROCA" };

        // Adiciona as casas especiais no tabuleiro, até alcançar a quantidade definida
        while (i < qtd_casas_especiais) {
            System.out.println();

            // Exibe os tipos de casas disponíveis
            for (int j = 0; j < 7; j++)
                System.out.printf("%d.%s ", j + 1, tipos_casas[j]);

            try {
                // Pergunta o tipo e a posição de cada casa especial
                System.out.printf("\n\nColoque o tipo da %d* casa: ", i + 1);
                scanner.nextLine();
                tipo_casa = scanner.nextLine().trim().toUpperCase();
                System.out.printf("Coloque a posicao da dela: ");
                pos = scanner.nextInt();

                // Se o tipo de casa ou a posição forem inválidos, lança uma exceção
                if (!tipoCasaValido(tipo_casa) || pos < 0 || pos >= qtd_casas)
                    throw new AcaoInvalidaException();

                // Ajusta a casa especial na posição indicada
                casas.set(pos, factory.ajustarCasa(tipo_casa));
                ++i;
            } catch (AcaoInvalidaException e) {
                // Trata exceções para posições e tipos de casas inválidos
                if (pos < 0 || pos >= qtd_casas)
                    e.excedeLimiteCasas();
                else
                    e.tipoCasaInvalido();
            }
        }
    }

    // Método para ajustar todas as casas do tabuleiro
    public void ajustarCasas(int qtd_casas) {
        int qtd_casas_especiais;

        // Preenche o array de casas com valores nulos
        for (int i = 0; i < qtd_casas; i++)
            casas.add(null);

        // Define o número de casas especiais e ajusta as casas do tabuleiro
        while (true) {
            try {
                System.out.print("\nInsira o numero de casas especiais: ");
                qtd_casas_especiais = scanner.nextInt();

                // Se o número de casas especiais for inválido, lança uma exceção
                if (qtd_casas_especiais < 0 || qtd_casas_especiais > qtd_casas)
                    throw new AcaoInvalidaException();

                // Ajusta as casas especiais no tabuleiro
                ajustarCasasEspeciais(qtd_casas_especiais, qtd_casas);

                // Preenche as casas restantes com casas simples
                for (int i = 0; i < casas.size(); i++) {
                    if (casas.get(i) == null)
                        casas.set(i, factory.ajustarCasa("SIMPLES"));
                }

                break;
            } catch (AcaoInvalidaException e) {
                // Trata exceção se o número de casas especiais for inválido
                e.excedeLimiteCasas();
            }
        }

    }

    // Método para ajustar os jogadores no tabuleiro
    public void ajustarJogadores(int qtd_jogs) {
        int tipo1, tipo2, tipo3;

        // Preenche o array de jogadores com valores nulos
        for (int i = 0; i < qtd_jogs; i++)
            jogs.add(null);

        // Ajusta os tipos de jogadores até que não existam jogadores do mesmo tipo
        while (true) {
            tipo1 = 0;
            tipo2 = 0;
            tipo3 = 0;

            try {
                ajustarTipoJogadores(qtd_jogs);

                // Conta quantos jogadores de cada tipo existem
                for (Jogador jog : jogs) {
                    if (jog instanceof JogadorNormal)
                        ++tipo1;
                    else if (jog instanceof JogadorSortudo)
                        ++tipo2;
                    else
                        ++tipo3;
                }

                // Se todos os jogadores forem do mesmo tipo, lança uma exceção
                if (tipo1 == qtd_jogs || tipo2 == qtd_jogs || tipo3 == qtd_jogs)
                    throw new AcaoInvalidaException();

                break;
            } catch (AcaoInvalidaException e) {
                // Trata exceção para tipos de jogadores iguais
                e.tiposIguais();
            }
        }
    }

    // Verifica se o tipo de jogador é válido
    private boolean tipoJogValido(String tipo_jog) {
        boolean tipo_valido = false;

        // Array com os tipos de jogadores válidos
        String tipos_jogs[] = { "NORMAL", "SORTUDO", "AZARADO" };

        // Verifica se o tipo de jogador informado existe no array de tipos válidos
        for (int i = 0; i < 3; i++) {
            if (tipo_jog.equals(tipos_jogs[i])) {
                tipo_valido = true;
                break;
            }
        }

        return tipo_valido;
    }

    // Verifica se a cor do jogador é válida
    private boolean corValida(String cor, ArrayList<String> cores) {
        boolean cor_valida = false;

        // Verifica se a cor está disponível e a remove da lista se for usada
        for (int i = 0; i < cores.size(); i++) {
            if (cor.equals(cores.get(i))) {
                cor_valida = true;
                cores.remove(i);
                break;
            }
        }
        return cor_valida;
    }

    // Método para ajustar os tipos de jogadores
    private void ajustarTipoJogadores(int qtd_jogs) {
        int i = 0;
        String tipo_jog = null, nome, cor;
        ArrayList<String> cores = new ArrayList<String>();

        // Lista de cores disponíveis para os jogadores
        cores.add("AZUL");
        cores.add("VERMELHO");
        cores.add("PRETO");
        cores.add("BRANCO");
        cores.add("LARANJA");
        cores.add("ROXO");

        scanner.nextLine(); // Consome a nova linha

        // Ajusta os jogadores até que todos os tipos e cores sejam válidos
        while (i < qtd_jogs) {
            System.out.println();

            try {
                // Mostra as cores disponíveis para escolha
                for (int j = 0; j < cores.size(); j++)
                    System.out.printf("%d.%s ", j + 1, cores.get(j));

                // Pergunta o nome, tipo e cor do jogador
                System.out.printf("\n\nColoque o nome do %d* jogador: ", i + 1);
                nome = scanner.nextLine().trim().toUpperCase();
                System.out.print("Escolha o tipo (normal, sortudo ou azarado): ");
                tipo_jog = scanner.nextLine().trim().toUpperCase();
                System.out.print("Escolha a cor: ");
                cor = scanner.nextLine().trim().toUpperCase();

                // Verifica se o tipo e a cor são válidos, senão lança uma exceção
                if (!tipoJogValido(tipo_jog) || !corValida(cor, cores))
                    throw new AcaoInvalidaException();

                // Ajusta o jogador com o tipo, nome, cor e posição
                jogs.set(i, factory.ajustarJogador(nome, cor, i, tipo_jog));
                ++i;
            } catch (AcaoInvalidaException e) {
                // Trata exceção para tipos ou cores inválidos
                if (!tipoJogValido(tipo_jog))
                    e.tipoNaoExistente();
                else
                    e.CorInexistente();
            }
        }
    }

    // Define a quantidade de casas que cada jogador pode mover
    public void atribuirQtdCasasJogs() {
        for (Jogador jog : jogs)
            jog.setQtdCasas(casas.size());
    }

    public void atualizarSiglas() {
        // Atualiza a posição dos jogadores na primeira casa
        for (Jogador jogador : jogs)
            casas.get(0).setSigla(jogador.getSigla(), jogador.getNumJogador());
    }

    // Imprime o estado inicial do tabuleiro e dos jogadores
    public void imprimirPrimeiraRodada() {
        jog.setMinhaVez(true);
        System.out.println();

        // Imprime o tabuleiro
        for (int i = 0; i < casas.size(); i++) {
            casas.get(i).imprimirCasa();

            if ((i + 1) % 10 == 0)
                System.out.println();
        }

        System.out.println();

        // Imprime as informações dos jogadores
        for (Jogador jogador : jogs)
            System.out.println(jogador.imprimirInfomacoes());

        // Solicita ao usuário para girar os dados
        System.out.print("\nPressione ENTER para girar os dados: ");
        scanner.nextLine();

        // Gira os dados e atualiza a posição do jogador
        dados = jog.girarDados();
        casas.get(jog.getCasaAtual()).setSigla(' ', jog.getNumJogador());
        jog.andarCasas(dados[0] + dados[1]);
        casas.get(jog.getCasaAtual()).setSigla(jog.getSigla(), jog.getNumJogador());
        casas.get(jog.getCasaAtual()).aplicarRegra(jog, jogs);
    }

    public void definirPrimeiroJogador() {
        jog = jogs.get(0);
    }

    // Continua com as rodadas do jogo
    public void continuarRodadas() {
        while (true) {
            limparTerminal();

            // Imprime o valor dos dados e a mensagem da casa atual
            System.out.printf("Valores dos dados: [%d][%d]\n%s\n\n", dados[0], dados[1],
                    casas.get(jog.getCasaAtual()).getMsg(jog));

            // Atualiza o jogador se não tiver dados iguais ou se não tiver que jogar
            // novamente
            if (dados[0] != dados[1] && !jog.getJogarNovamente())
                jog = atualizarJogador(jog, jogs);

            // Se o jogador tiver que jogar novamente, reseta o flag
            if (jog.getJogarNovamente())
                jog.setJogarNovamente(false);

            // Se o turno do jogador estiver bloqueado, imprime o estado do tabuleiro e
            // jogadores
            if (jog.getVezBloqueada()) {
                jog.setVezBloqueada(false);

                jog.setMinhaVez(true);
                System.out.println();

                for (Jogador jogador : jogs)
                    casas.get(0).setSigla(jogador.getSigla(), jogador.getNumJogador());

                for (int i = 0; i < casas.size(); i++) {
                    casas.get(i).imprimirCasa();

                    if ((i + 1) % 10 == 0)
                        System.out.println();
                }

                System.out.println();

                for (Jogador jogador : jogs)
                    System.out.println(jogador.imprimirInfomacoes());

                System.out.print("\nPressione ENTER para girar os dados: ");
                scanner.nextLine();
                continue;
            }

            // Imprime o estado inicial do tabuleiro e dos jogadores
            imprimirPrimeiraRodada();

            // Encerra o loop se o jogador chegou ao final do tabuleiro
            if (jog.getCasaAtual() == casas.size() - 1)
                break;
        }
    }

    // Atualiza o jogador atual para o próximo na lista
    private Jogador atualizarJogador(Jogador jog, ArrayList<Jogador> jogs) {
        int indice = jog.getNumJogador();

        jog.setMinhaVez(false);
        indice = (indice + 1) % jogs.size();
        jog = jogs.get(indice);

        return jog;
    }

    // Limpa o terminal de acordo com o sistema operacional
    private static void limparTerminal() {
        String os = System.getProperty("os.name").toLowerCase();

        try {
            if (os.contains("win")) {
                // Para Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
                // Para Linux e macOS
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
