public class Jogo {
    Tabuleiro tabuleiro = Tabuleiro.instanciar();

    public void configTabuleiro(int qtd_casas) {
        tabuleiro.configurarCasas(qtd_casas);
    }

    public void config(int qtd_jogs) {
        tabuleiro.configurarJogadores(qtd_jogs);
    }

    public void printTabuleiro(int qtd_casas, boolean modo_debug) {
        tabuleiro.setModoDebug(modo_debug);
        tabuleiro.ajustarPrimeiroJogador(qtd_casas);
        tabuleiro.comecarRodada();
    }

    public void start() {
        tabuleiro.continuarRodadas();
    }
}
