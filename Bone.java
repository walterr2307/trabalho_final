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

    public void addMoedas(int novas_moedas) {
        if (novas_moedas == 1)
            jog.addMoedas(2);
        else
            jog.addMoedas(novas_moedas);
    }
}
