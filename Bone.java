public class Bone extends Decorador {

    public Bone(Jogador jog) {
        super(jog);
    }

    @Override
    public void ganharUmaMoeda() {
        jog.ganharUmaMoeda();
        qtd_moedas = jog.getQtdMoedas() + 1;
    }

    @Override
    public int[] girarDados() {
        return jog.girarDados(); // Mantém o comportamento de girar dados
    }

    @Override
    public String imprimirInformacoes() {
        return jog.imprimirInformacoes() + " --> bone"; // Adiciona o decorador "bone" na saída
    }
}
