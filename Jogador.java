public abstract class Jogador {
    // Atributos protegidos que definem o estado do jogador
    protected int qtd_moedas = 0, casa_atual = 0, num_jogadas = 0, qtd_casas;
    protected boolean minha_vez = false, vez_bloqueada = false, jogar_novamente = false;
    protected char seta_vez = ' '; // Indica visualmente se é a vez do jogador
    protected String cor, tipo_jog; // Cor do jogador e tipo (podem ser usadas para personalização ou lógica)

    // Construtor da classe Jogador, inicializa cor e tipo de jogador
    public Jogador(String cor, String tipo_jog) {
        this.cor = cor;
        this.tipo_jog = tipo_jog;
    }

    // Método abstrato que será implementado pelas subclasses para realizar a ação
    // de girar dados
    public abstract int[] girarDados();

    // Método para o jogador ganhar uma moeda
    public void ganharUmaMoeda() {
        ++qtd_moedas;
    }

    // Retorna o número de jogadas feitas pelo jogador
    public int getNumJogadas() {
        return num_jogadas;
    }

    // Define o número de jogadas feitas pelo jogador
    public void setNumJogadas(int num_jogadas) {
        this.num_jogadas = num_jogadas;
    }

    // Incrementa o número de jogadas feitas
    public void addNumJogadas() {
        ++num_jogadas;
    }

    // Retorna a cor do jogador
    public String getCor() {
        return cor;
    }

    // Retorna o tipo do jogador
    public String getTipo() {
        return tipo_jog;
    }

    // Define o número de casas no tabuleiro
    public void setQtdCasas(int qtd_casas) {
        this.qtd_casas = qtd_casas;
    }

    // Retorna o número total de casas no tabuleiro
    public int getQtdCasas() {
        return qtd_casas;
    }

    // Imprime as informações do jogador (cor, tipo, casa atual, moedas), formatando
    // se for a vez dele
    public String imprimirInformacoes() {
        String formato;

        // Se for a vez do jogador, seta_vez recebe '*', caso contrário ' '
        if (minha_vez)
            seta_vez = '*';
        else
            seta_vez = ' ';

        // Retorna uma string formatada com os detalhes do jogador
        formato = String.format("%c  Jogador %s (%s, casa %d): %d moedas", seta_vez, cor, tipo_jog, casa_atual,
                qtd_moedas);

        return formato;
    }

    // Retorna a casa atual do jogador
    public int getCasaAtual() {
        return casa_atual;
    }

    // Adiciona moedas ao jogador, garantindo que a quantidade mínima de moedas seja
    // 0
    public void addMoedas(int novas_moedas) {
        qtd_moedas += novas_moedas;

        // Garante que o número de moedas não fique negativo
        if (qtd_moedas < 0)
            qtd_moedas = 0;
    }

    // Retorna a quantidade de moedas do jogador
    public int getQtdMoedas() {
        return qtd_moedas;
    }

    // Retorna se é a vez do jogador
    public boolean getMinhaVez() {
        return minha_vez;
    }

    // Define se é a vez do jogador
    public void setMinhaVez(boolean minha_vez) {
        this.minha_vez = minha_vez;
    }

    // Retorna se a vez do jogador está bloqueada
    public boolean getVezBloqueada() {
        return vez_bloqueada;
    }

    // Define se a vez do jogador está bloqueada
    public void setVezBloqueada(boolean vez_bloqueada) {
        this.vez_bloqueada = vez_bloqueada;
    }

    // Retorna se o jogador pode jogar novamente
    public boolean getJogarNovamente() {
        return jogar_novamente;
    }

    // Define se o jogador pode jogar novamente
    public void setJogarNovamente(boolean jogar_novamente) {
        this.jogar_novamente = jogar_novamente;
    }

    // Define a casa atual do jogador
    public void setCasaAtual(int casa_atual) {
        this.casa_atual = casa_atual;

        if (this.casa_atual < 0)
            this.casa_atual = 0;
        if (this.casa_atual > qtd_casas - 1)
            this.casa_atual = qtd_casas - 1;
    }

    // Define a quantidade de moedas do jogador
    public void setQtdMoedas(int qtd_moedas) {
        this.qtd_moedas = qtd_moedas;
    }

    // Método que faz o jogador andar um determinado número de casas
    public void andarCasas(int passos) {
        casa_atual += passos;

        // Garante que o jogador não ultrapasse os limites do tabuleiro
        if (casa_atual > qtd_casas - 1)
            casa_atual = qtd_casas - 1;
        if (casa_atual < 0)
            casa_atual = 0;
    }

    public void atualizarInfos() {

    }
}
