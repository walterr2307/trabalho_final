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

    public void addMoedas(int novas_moedas) {
        if (novas_moedas == 4)
            jog.addMoedas(7);
        else
            jog.addMoedas(novas_moedas);
    }
}
