public class JogadorNormal extends Jogador {

    public JogadorNormal(String cor, String tipo_jog) {
        super(cor, tipo_jog);
    }

    public int[] girarDados() {
        int dados[] = new int[2];

        dados[0] = (int) (Math.random() * 6 + 1);
        dados[1] = (int) (Math.random() * 6 + 1);

        return dados;
    }
}
