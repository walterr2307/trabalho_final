import java.util.ArrayList;

public class CasaJogarDeNovo extends Casa {

    public void aplicarRegra(Jogador jog, ArrayList<Jogador> jogs) {
        jog.setJogarNovamente(true);
    }

    public String getMsg(Jogador jog) {
        return "Voce jogou novamente!";
    }
}
