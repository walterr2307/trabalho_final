public class OculosEscuros extends Decorator {
    Jogador jog;

    public OculosEscuros(Jogador jog) {
        super(jog);
        this.jog = jog;
    }

    public int[] girarDados() {
        return jog.girarDados();
    }

    public String imprimirInformacoes() {
        return jog.imprimirInfomacoes() + ", Oculos Escuros";
    }
}
