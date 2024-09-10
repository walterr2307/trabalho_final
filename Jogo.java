public class Jogo {
    private Tabuleiro tabuleiro = Tabuleiro.instanciar();

    public void configTabuleiro(int qtd_casas) {
        tabuleiro.ajustarCasas(qtd_casas);
    }

    public void config(int qtd_jogs) {
        tabuleiro.ajustarJogadores(qtd_jogs);
    }

    public void printTabuleiro() {
        tabuleiro.definirPrimeiroJogador();
        tabuleiro.atribuirQtdCasasJogs();
        tabuleiro.imprimirPrimeiraRodada();
        tabuleiro.imprimirInfoJogadores();
    }

    public void start() {
        tabuleiro.continuarRodadas();
    }
}