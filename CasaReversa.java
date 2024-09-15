import java.util.ArrayList;

public class CasaReversa extends Casa {

    // Método que aplica a regra da Casa Reversa
    // Faz com que o jogador atual troque de posição com o jogador que está mais
    // atrás no tabuleiro
    public void aplicarRegra(int indice, Jogador jog, ArrayList<Jogador> jogs) {
        int qtd_passos; // Armazena a quantidade de passos para a troca
        Jogador ultimo_jog = jogs.get(0); // Inicializa o último jogador como o primeiro da lista

        // Itera sobre a lista de jogadores para encontrar o que está mais atrás no
        // tabuleiro
        for (int i = 0; i < jogs.size(); i++) {
            if (jogs.get(i).getCasaAtual() < ultimo_jog.getCasaAtual()) // Compara a posição atual dos jogadores
                ultimo_jog = jogs.get(i); // Atualiza o último jogador se encontrar alguém mais atrás
        }

        // Calcula a diferença de posições entre o jogador atual e o último jogador
        qtd_passos = jog.getCasaAtual() - ultimo_jog.getCasaAtual();

        // O jogador atual anda para trás a quantidade de passos calculada
        jog.andarCasas(-qtd_passos);

        // O último jogador anda para frente a mesma quantidade de passos
        ultimo_jog.andarCasas(qtd_passos);
    }

    // Retorna uma mensagem indicando que houve uma troca de posições entre
    // jogadores
    public String getMsg(Jogador jog) {
        return "Troca de posicoes entre jogadores!";
    }
}
