public class Bone extends Decorator {
    Jogador jog;

    public Bone(Jogador jog) {
        super(jog);
        this.jog = jog;
    }

    public int[] girarDados() {
        return jog.girarDados();
    }

    public String imprimirInformacoes() {
        return jog.imprimirInfomacoes() + " --> Bone";
    }
}
