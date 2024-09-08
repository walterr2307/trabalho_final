import java.util.ArrayList;

public abstract class Casa {
    private int num_casa;
    private char siglas[] = { ' ', ' ', ' ', ' ', ' ', ' ' };

    public abstract void aplicarRegra(Jogador jog, ArrayList<Jogador> jogs);

    public abstract String getMsg(Jogador jog);

    public void setNumCasa(int num_casa) {
        this.num_casa = num_casa;
    }

    public int getNumCasa() {
        return this.num_casa;
    }

    public void setSigla(char sigla, int num_jog) {
        siglas[num_jog] = sigla;
    }

    public void imprimirCasa() {
        System.out.printf("[%c%c%c%c%c%c]", siglas[0], siglas[1], siglas[2], siglas[3], siglas[4], siglas[5]);
    }
}
