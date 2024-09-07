import java.util.ArrayList;
import java.util.Scanner;

public class Jogo {

    // Método para escolher a quantidade de jogadores.
    // Recebe um objeto Scanner para capturar a entrada do usuário.
    public int escolherQtdJogadores(Scanner scanner) {
        int qtd = 0;

        // Loop para garantir que a quantidade de jogadores esteja dentro do limite
        // aceitável.
        while (true) {
            try {
                System.out.print("Insira a quantidade de jogadores: ");
                qtd = scanner.nextInt();

                // Verifica se a quantidade de jogadores está entre 2 e 6.
                if (qtd < 2 || qtd > 6)
                    throw new AcaoInvalidaException(); // Lança uma exceção personalizada para quantidades inválidas.

                break; // Sai do loop se a quantidade for válida.
            } catch (AcaoInvalidaException e) {
                e.QtdJogsInvalida(); // Informa que a quantidade inserida é inválida.
            }
        }
        return qtd; // Retorna a quantidade de jogadores válida.
    }

    // Método para escolher o nome e a cor de cada jogador.
    // Recebe listas para armazenar os nomes e cores dos jogadores, a quantidade de
    // jogadores, e um Scanner para entrada do usuário.
    public void escolherNomeCor(ArrayList<String> nomes, ArrayList<String> cores, int qtd_jogs, Scanner scanner) {
        int i = 0, indice_cor;
        String nome;
        ArrayList<String> cores_disponiveis = new ArrayList<String>();

        // Lista de cores disponíveis para os jogadores.
        cores_disponiveis.add("Branco");
        cores_disponiveis.add("Azul");
        cores_disponiveis.add("Vermelho");
        cores_disponiveis.add("Preto");
        cores_disponiveis.add("Roxo");
        cores_disponiveis.add("Laranja");

        // Loop para definir o nome e a cor de cada jogador.
        while (i < qtd_jogs) {
            try {
                System.out.println();

                // Exibe as cores disponíveis com seus índices.
                for (int j = 0; j < cores_disponiveis.size(); j++)
                    System.out.printf("%d.%s  ", j + 1, cores_disponiveis.get(j));

                scanner.nextLine(); // Consome a nova linha para evitar problemas na captura da entrada.
                System.out.println();

                // Captura o nome do jogador.
                System.out.printf("\nPonha o nome do %d* jogador: ", i + 1);
                nome = scanner.nextLine().trim().toUpperCase(); // Recebe o nome do jogador e converte para maiúsculas.

                // Captura o índice da cor escolhida.
                System.out.print("Selecione o indice da cor: ");
                indice_cor = scanner.nextInt() - 1; // Converte a escolha de cor para índice válido.

                // Verifica se o índice está dentro do limite de cores disponíveis.
                if (indice_cor < 0 || indice_cor >= cores_disponiveis.size())
                    throw new AcaoInvalidaException(); // Lança exceção se a cor não existir.

                // Adiciona o nome e a cor escolhida nas respectivas listas.
                nomes.add(nome);
                cores.add(cores_disponiveis.get(indice_cor));
                cores_disponiveis.remove(indice_cor); // Remove a cor escolhida da lista de disponíveis.

                ++i; // Passa para o próximo jogador.
            } catch (AcaoInvalidaException e) {
                e.CorInexistente(); // Informa que a cor selecionada é inválida.
            }
        }
    }

    // Método privado para verificar se todos os jogadores escolheram o mesmo tipo.
    private boolean retornarTiposIguais(ArrayList<Integer> tipos) {
        int tipo1 = 0, tipo2 = 0, tipo3 = 0, tam = tipos.size(); // Inicializa contadores para cada tipo.
        boolean tipo_iguais = false;

        // Contabiliza a quantidade de cada tipo entre os jogadores.
        for (int i = 0; i < tam; i++) {
            if (tipos.get(i) == 0)
                ++tipo1; // Conta quantos jogadores escolheram o tipo "Normal".
            else if (tipos.get(i) == 1)
                ++tipo2; // Conta quantos jogadores escolheram o tipo "Sortudo".
            else
                ++tipo3; // Conta quantos jogadores escolheram o tipo "Azarado".
        }

        // Se todos os jogadores escolheram o mesmo tipo, define como verdadeiro.
        if (tipo1 == tam || tipo2 == tam || tipo3 == tam)
            tipo_iguais = true;

        return tipo_iguais; // Retorna se todos os tipos são iguais.
    }

    // Método para permitir que cada jogador escolha um tipo (Normal, Sortudo,
    // Azarado).
    public void escolherTipo(ArrayList<String> nomes, ArrayList<Integer> tipos, int tam, Scanner scanner) {
        int i = 0, tipo_escolhido;
        String tipos_disponiveis[] = { "Normal", "Sortudo", "Azarado" }; // Define os tipos disponíveis.

        while (true) {
            while (i < tam) {
                try {
                    System.out.println();

                    // Exibe os tipos disponíveis para seleção.
                    for (int j = 0; j < 3; j++)
                        System.out.printf("%d.%s  ", j + 1, tipos_disponiveis[j]);

                    // Pede ao jogador para escolher o tipo desejado.
                    System.out.printf("\nInsira o tipo de %s: ", nomes.get(i));
                    tipo_escolhido = scanner.nextInt() - 1; // Converte a escolha do tipo para índice válido.

                    // Verifica se o tipo escolhido é válido.
                    if (tipo_escolhido < 0 || tipo_escolhido > 2)
                        throw new AcaoInvalidaException(); // Lança exceção se o tipo não existir.

                    tipos.add(tipo_escolhido); // Adiciona o tipo escolhido à lista.
                    ++i;
                } catch (AcaoInvalidaException e) {
                    e.tipoNaoExistente(); // Informa que o tipo selecionado é inválido.
                }
            }

            // Verifica se todos os jogadores escolheram tipos diferentes.
            if (!retornarTiposIguais(tipos))
                break; // Sai do loop se houver pelo menos dois tipos diferentes.

            i = 0; // Reseta o índice para permitir nova escolha de tipos.
            tipos = new ArrayList<Integer>(); // Reseta a lista de tipos.
            System.out.println("\nEscolha, pelo menos, dois tipos diferentes!\n");
        }
    }

    // Método para gerar o tabuleiro do jogo com base nos nomes, cores e tipos dos
    // jogadores.
    public void gerarTabuleiro(ArrayList<String> nomes, ArrayList<String> cores, ArrayList<Integer> tipos,
            Scanner scanner) {
        Tabuleiro tabuleiro = new Tabuleiro(nomes, cores, tipos, scanner); // Cria uma instância da classe Tabuleiro.
        tabuleiro.iniciarRodadas();
    }
}
