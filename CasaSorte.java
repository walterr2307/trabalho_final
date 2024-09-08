import java.util.ArrayList;

public class CasaSorte extends Casa {

    public void aplicarRegra(Jogador jog, ArrayList<Jogador> jogs) {
        jog.andarCasas(3);
    }

    public String getMsg(Jogador jog) {
        return "Casa da Sorte! Andou 3 casas.";
    }
}
