public class JogadorSortudo extends Jogador {

    public JogadorSortudo(String cor, String tipo_jog) {
        super(cor, tipo_jog);
    }

    public int[] girarDados() {
        int dados[] = new int[2];

        do {
            dados[0] = (int) (Math.random() * 6 + 1);
            dados[1] = (int) (Math.random() * 6 + 1);
        } while (dados[0] + dados[1] < 7);

        return dados;
    }
}
