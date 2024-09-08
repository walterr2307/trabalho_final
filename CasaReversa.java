import java.util.ArrayList;

public class CasaReversa extends Casa {
    public void aplicarRegra(Jogador jog, ArrayList<Jogador> jogs) {
        int qtd_passos;
        Jogador ultimo_jog = jogs.get(0);

        for (Jogador aux_jog : jogs) {
            if (aux_jog.getCasaAtual() < ultimo_jog.getCasaAtual())
                ultimo_jog = aux_jog;
        }

        qtd_passos = jog.getCasaAtual() - ultimo_jog.getCasaAtual();
        jog.andarCasas(-qtd_passos);
        ultimo_jog.andarCasas(qtd_passos);
    }

    public String getMsg(Jogador jog) {
        return "Houve troca de casas entre jogadores!";
    }
}
