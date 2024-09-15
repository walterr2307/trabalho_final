import java.util.ArrayList;

public abstract class Casa {

    public abstract void aplicarRegra(int indice, Jogador jog, ArrayList<Jogador> jogs);

    public abstract String getMsg(Jogador jog);
}
