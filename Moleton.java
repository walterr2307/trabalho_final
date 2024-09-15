public class Moleton extends Decorador {

    public Moleton(Jogador jog) {
        super(jog);
    }

    @Override
    public void ganharUmaMoeda() {
        jog.ganharUmaMoeda();
        qtd_moedas = jog.getQtdMoedas() + 2;
    }

    @Override
    public String imprimirInformacoes() {
        return jog.imprimirInformacoes() + ", moleton"; // Adiciona o decorador "moleton" na saída
    }

    @Override
    public int[] girarDados() {
        return jog.girarDados(); // Mantém o comportamento de girar dados
    }
}
