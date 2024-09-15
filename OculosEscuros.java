public class OculosEscuros extends Decorador {

    public OculosEscuros(Jogador jog) {
        super(jog);
    }

    @Override
    public void ganharUmaMoeda() {
        jog.ganharUmaMoeda();
        qtd_moedas = jog.getQtdMoedas() + 3;
    }

    @Override
    public String imprimirInformacoes() {
        return jog.imprimirInformacoes() + ", oculos escuros"; // Adiciona o decorador "oculos escuros" na saída
    }

    @Override
    public int[] girarDados() {
        return jog.girarDados(); // Mantém o comportamento de girar dados
    }
}
