public abstract class Decorator extends Jogador {

    public Decorator(Jogador jog) {
        super(jog.getNome(), jog.getCor(), jog.getNumJogador(), jog.getTipoJogador());
    }
}
