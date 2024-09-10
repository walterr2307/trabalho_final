public class Moleton extends Decorator {
    Jogador jog;

    public Moleton(Jogador jog) {
        super(jog);
        this.jog = jog;
    }

    public int[] girarDados() {
        return jog.girarDados();
    }

    public void addMoedas(int novas_moedas) {
        if (novas_moedas == 2)
            jog.addMoedas(4);
        else
            jog.addMoedas(novas_moedas);
    }

    public String imprimirInformacoes() {
        return jog.imprimirInfomacoes() + ", Moleton";
    }
}