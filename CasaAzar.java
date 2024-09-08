import java.util.ArrayList;

public class CasaAzar extends Casa {
    
    public void aplicarRegra(Jogador jog, ArrayList<Jogador> jogs) {
        if (!(jog instanceof JogadorSortudo))
            jog.addMoedas(-3);
    }

    public String getMsg(Jogador jog) {
        if (jog instanceof JogadorSortudo)
            return "Casa do Azar, mas voce teve sorte!";
        else
            return "Perdeu 3 moedas!";
    }
}
