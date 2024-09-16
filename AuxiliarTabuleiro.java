import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AuxiliarTabuleiro {
    private static AuxiliarTabuleiro instancia; // Instância única do Auxiliar Tabuleiro
    private int indice_cor; // Armazena o índice da cor selecionada
    private Fabrica fabrica = new Fabrica(); // Instância da fábrica que cria objetos de Casas e Jogadores
    private String tipos_jog[] = { "normal", "sortudo", "azarado" }; // Tipos de jogadores possíveis
    private String tipos_casa[] = { "azar", "jogar de novo", "prisao", "reversa", "sorte", "surpresa", "troca" };
    private ArrayList<String> cores; // Lista de cores disponíveis para os jogadores
    private ArrayList<Casa> casas; // Lista que armazena as casas do tabuleiro
    private ArrayList<Jogador> jogs; // Lista que armazena os jogadores
    private Scanner scanner = new Scanner(System.in); // Scanner para entrada de dados

    // Construtor que recebe a lista de casas e jogadores
    private AuxiliarTabuleiro(ArrayList<Casa> casas, ArrayList<Jogador> jogs) {
        this.casas = casas;
        this.jogs = jogs;
    }

    public static AuxiliarTabuleiro instanciar(ArrayList<Casa> casas, ArrayList<Jogador> jogs) {
        if (instancia == null) {
            instancia = new AuxiliarTabuleiro(casas, jogs); // Cria uma nova instância se não houver nenhuma
        }
        return instancia;
    }

    // Método para configurar as casas do tabuleiro
    public void configurarCasas(int qtd_casas) {
        int qtd_casas_especiais = 0;

        // Inicializa a lista de casas com 'null'
        for (int i = 0; i < qtd_casas; i++)
            casas.add(null);

        while (true) {
            try {

                // Solicita ao usuário a quantidade de casas especiais
                while (true) {
                    try {
                        System.out.print("\nInsira a quantidade de casas especiais: ");
                        qtd_casas_especiais = scanner.nextInt();
                        break; // Sai do loop se a entrada for válida
                    } catch (InputMismatchException e) {
                        System.out.println("Por favor, digite um número inteiro válido.");
                        scanner.next(); // Limpa a entrada inválida
                    }
                }

                // Verifica se a quantidade de casas especiais é válida
                if (qtd_casas_especiais < 0 || qtd_casas_especiais >= qtd_casas)
                    throw new AcaoInvalidaException();

                // Configura as casas especiais
                for (int i = 0; i < qtd_casas_especiais; i++)
                    configurarCasasEspeciais(i);

                // Configura as casas simples nas posições que não foram preenchidas
                for (int i = 0; i < casas.size(); i++) {
                    if (casas.get(i) == null) {
                        casas.set(i, fabrica.retornarCasa("simples"));
                    }
                }

                break;
            } catch (AcaoInvalidaException e) {
                e.excedeuQtdCasas(); // Mensagem de erro para quantidade de casas inválida
            }
        }
    }

    // Método auxiliar para configurar as casas especiais
    private void configurarCasasEspeciais(int i) {
        int pos = 0;
        String tipo_casa = null;

        while (true) {
            System.out.println();

            // Mostra os tipos de casas especiais
            for (int j = 0; j < tipos_casa.length; j++)
                System.out.printf("%d.%s ", j + 1, tipos_casa[j]);

            try {
                // Solicita o tipo da casa especial
                System.out.printf("\n\nEscolha o tipo da %d* casa especial: ", i + 1);
                scanner.nextLine();
                tipo_casa = scanner.nextLine().trim().toLowerCase();

                // Solicita a posição da casa especial
                while (true) {
                    try {
                        System.out.print("Insira a posicao da casa: ");
                        pos = scanner.nextInt();
                        break; // Sai do loop se a entrada for válida
                    } catch (InputMismatchException e) {
                        System.out.println("Por favor, digite um número inteiro válido.");
                        scanner.next(); // Limpa a entrada inválida
                    }
                }

                // Valida se o tipo da casa e a posição são válidos
                if (!retornarTipoValido(tipo_casa, tipos_casa) || pos < 1 || pos >= casas.size())
                    throw new AcaoInvalidaException();

                // Configura a casa especial na posição selecionada
                casas.set(pos, fabrica.retornarCasa(tipo_casa));
                pos += 3; // Aumenta a posição para evitar sobreposição
                break;
            } catch (AcaoInvalidaException e) {
                // Tratamento de erros
                if (pos < 1 || pos >= casas.size())
                    e.excedeuQtdCasas();
                else
                    e.tipoInvalido();
            }
        }
    }

    // Verifica se o tipo de casa é válido
    private boolean retornarTipoValido(String tipo, String vetor[]) {
        boolean tipo_valido = false;

        for (int i = 0; i < vetor.length; i++) {
            if (tipo.equals(vetor[i])) {
                tipo_valido = true;
                break;
            }
        }
        return tipo_valido;
    }

    // Verifica se a cor do jogador é válida
    private boolean retornarCorValida(String cor) {
        boolean cor_valida = false;

        for (int i = 0; i < cores.size(); i++) {
            if (cor.equals(cores.get(i))) {
                indice_cor = i; // Armazena o índice da cor selecionada
                cor_valida = true;
                break;
            }
        }

        return cor_valida;
    }

    // Método para configurar os jogadores
    public void configurarJogadores(int qtd_jogs) {
        int tipo1 = 0, tipo2 = 0, tipo3 = 0; // Contadores para os tipos de jogadores

        scanner.nextLine(); // Limpa o buffer do scanner

        // Inicializa a lista de jogadores
        for (int i = 0; i < qtd_jogs; i++)
            jogs.add(null);

        while (true) {
            cores = new ArrayList<String>(); // Inicializa a lista de cores disponíveis

            cores.add("azul");
            cores.add("vermelho");
            cores.add("branco");
            cores.add("preto");
            cores.add("roxo");
            cores.add("laranja");

            try {
                configurarTipoJogs(qtd_jogs); // Configura os tipos de jogadores

                // Conta quantos jogadores de cada tipo foram configurados
                for (Jogador jog : jogs) {
                    if (jog instanceof JogadorNormal)
                        ++tipo1;
                    else if (jog instanceof JogadorSortudo)
                        ++tipo2;
                    else
                        ++tipo3;
                }

                // Verifica se todos os jogadores são do mesmo tipo, o que não é permitido
                if (tipo1 == qtd_jogs || tipo2 == qtd_jogs || tipo3 == qtd_jogs)
                    throw new AcaoInvalidaException();

                break;
            } catch (AcaoInvalidaException e) {
                e.tiposIguais(); // Mensagem de erro para jogadores de tipos iguais
                tipo1 = 0;
                tipo2 = 0;
                tipo3 = 0;
            }
        }

        System.out.println();
    }

    // Método auxiliar para configurar os tipos e cores dos jogadores
    private void configurarTipoJogs(int qtd_jogs) {
        int i = 0;
        String cor = null, tipo_jog = null;

        while (i < qtd_jogs) {
            System.out.println();

            // Mostra as cores disponíveis
            for (int j = 0; j < cores.size(); j++)
                System.out.printf("%d.%s ", j + 1, cores.get(j));

            try {
                // Solicita a cor e o tipo do jogador
                System.out.printf("\n\nEscolha a cor do %d jogador: ", i + 1);
                cor = scanner.nextLine().trim().toLowerCase();
                System.out.print("Escolha o tipo(normal, sortudo ou azarado): ");
                tipo_jog = scanner.nextLine().trim().toLowerCase();

                // Valida a cor e o tipo do jogador
                if (!(retornarCorValida(cor) && retornarTipoValido(tipo_jog, tipos_jog)))
                    throw new AcaoInvalidaException();

                // Remove a cor selecionada da lista de cores disponíveis
                cores.remove(indice_cor);
                jogs.set(i, fabrica.retornarJogador(tipo_jog, cor)); // Adiciona o jogador à lista
                ++i;
            } catch (AcaoInvalidaException e) {
                // Tratamento de erros
                if (!retornarTipoValido(tipo_jog, tipos_jog))
                    e.tipoInvalido();
                else
                    e.corInvalida();
            }
        }
    }
}
