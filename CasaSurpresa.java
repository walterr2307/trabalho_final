import java.util.ArrayList;

public class CasaSurpresa extends Casa {

    public void aplicarRegra(Jogador jog, ArrayList<Jogador> jogs) {
        int j = 0, tipos_jog[] = new int[2], sorteio = (int) (Math.random() * 2);

        for (int i = 0; i < 3; i++) {
            if (i != jog.getTipoJogador()) {
                tipos_jog[j] = i;
                ++j;
            }
        }

        if (sorteio == 1) {
            sorteio = tipos_jog[0];
            tipos_jog[0] = tipos_jog[1];
            tipos_jog[1] = sorteio;
        }

        
    }
}
