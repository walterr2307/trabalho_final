public abstract class Decorador extends Jogador {
    protected Jogador jog;

    public Decorador(Jogador jog) {
        super(jog.getCor(), jog.getTipo());
        this.jog = jog;
        qtd_casas = jog.getQtdCasas();
        casa_atual = jog.getCasaAtual();
        qtd_moedas = jog.getQtdMoedas();
        num_jogadas = jog.getNumJogadas();
    }

    public void atualizarInfos() {
        jog.setMinhaVez(minha_vez);
        jog.setCasaAtual(casa_atual);
        jog.setQtdMoedas(qtd_moedas);
        jog.atualizarInfos();
    }

}
