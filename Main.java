import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int qtd_jogs;
        Scanner scanner = new Scanner(System.in);
        Jogo jogo = new Jogo();
        ArrayList<String> nomes = new ArrayList<String>();
        ArrayList<String> cores = new ArrayList<String>();
        ArrayList<Integer> tipos = new ArrayList<Integer>();

        qtd_jogs = jogo.escolherQtdJogadores(scanner);
        jogo.escolherNomeCor(nomes, cores, qtd_jogs, scanner);
        jogo.escolherTipo(nomes, tipos, qtd_jogs, scanner);
        jogo.gerarTabuleiro(nomes, cores, tipos, scanner);

        scanner.close();
    }
}