public class Moleton extends Decorator {
    Jogador jog;

    public Moleton(Jogador jog) {
        super(jog);
        this.jog = jog;
    }

    public int[] girarDados() {
        return jog.girarDados();
    }

    public String imprimirInformacoes() {
        return jog.imprimirInfomacoes() + ", Moleton";
    }
}