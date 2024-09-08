public abstract class Jogador {
    protected int tipo_jog, num_moedas, num_jog, casa_atual;
    protected boolean minha_vez, vez_bloqueada, jogar_novamente;
    protected char sigla;
    protected String nome, cor, str_tipo_jogador;

    public Jogador(String nome, String cor, int num_jog, int tipo_jog) {
        this.nome = nome;
        this.cor = cor;
        this.num_jog = num_jog;
        this.tipo_jog = tipo_jog;
        this.sigla = cor.charAt(0);
        this.num_moedas = 0;
        this.casa_atual = 0;
        this.minha_vez = false;
        this.vez_bloqueada = false;
        this.jogar_novamente = false;
        this.str_tipo_jogador = definirTipoJogador();
    }

    public abstract int[] girarDados();

    public void setJogarNovamente(boolean jogar_novamente) {
        this.jogar_novamente = jogar_novamente;
    }

    public boolean getJogarNovamente() {
        return this.jogar_novamente;
    }

    public void setVezBloqueada(boolean vez_bloqueada) {
        this.vez_bloqueada = vez_bloqueada;
    }

    public boolean getVezBloqueada() {
        return vez_bloqueada;
    }

    public int getNumMoedas() {
        return this.num_moedas;
    }

    public void setMinhaVez(boolean minha_vez) {
        this.minha_vez = minha_vez;
    }

    public boolean getMinhaVez() {
        return this.minha_vez;
    }

    public String imprimirInfomacoes() {
        char letra = ' ';
        String formato;

        if (minha_vez)
            letra = '*';

        formato = String.format("%c %s (%s, %s): %d moedas", letra, nome, str_tipo_jogador, cor, num_moedas);
        return formato;
    }

    public String getNome() {
        return this.nome;
    }

    public String getCor() {
        return this.cor;
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

        if (this.num_moedas < 0)
            this.num_moedas = 0;
    }

    public void atualizarDados(int num_moedas, int casa_atual) {
        this.num_moedas = num_moedas;
        this.casa_atual = casa_atual;
    }

    protected String definirTipoJogador() {
        if (tipo_jog == 0)
            return "Normal";
        else if (tipo_jog == 1)
            return "Sortudo";
        else
            return "Azarado";
    }
}
