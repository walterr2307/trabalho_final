public abstract class Jogador {
    private int tipo_jog, num_moedas, num_jog, casa_atual;
    private char sigla;
    private String nome, cor;

    public Jogador(String nome, String cor, int num_jog, int tipo_jog) {
        this.nome = nome;
        this.cor = cor;
        this.num_jog = num_jog;
        this.tipo_jog = tipo_jog;
        this.sigla = cor.charAt(0);
        this.num_moedas = 0;
        this.casa_atual = 0;
    }

    public abstract int[] girarDados();

    public void imprimirInfomacoes() {
        System.out.printf("%s(%s), %d moedas\n", nome, cor, num_moedas);
    }

    public int getTipoJogador() {
        return this.tipo_jog;
    }

    public char getSigla() {
        return this.sigla;
    }

    public int getNumJogador() {
        return this.num_jog;
    }

    public void andarCasas(int qtd_passos) {
        this.casa_atual += qtd_passos;

        if (this.casa_atual > 40)
            this.casa_atual = 40;
    }

    public int getCasaAtual() {
        return this.casa_atual;
    }

    public void addMoedas(int novas_moedas) {
        this.num_moedas += novas_moedas;
    }
}
