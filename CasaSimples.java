import java.util.ArrayList;

public class CasaSimples extends Casa {

    public void aplicarRegra(Jogador jog, ArrayList<Jogador> jogs) {
        jog.addMoedas(1);
    }

    public String getMsg(Jogador jog) {
        return "Ganhou uma moeda!";
    }
}
