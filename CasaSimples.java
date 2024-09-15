import java.util.ArrayList;

public class CasaSimples extends Casa {

    // Método que aplica a regra da Casa Simples
    // Concede ao jogador uma moeda extra
    public void aplicarRegra(int indice, Jogador jog, ArrayList<Jogador> jogs) {
        jog.ganharUmaMoeda(); // O jogador ganha uma moeda ao parar nesta casa
    }

    // Método que retorna uma mensagem indicando que o jogador ganhou uma moeda
    public String getMsg(Jogador jog) {
        return "Ganhou moeda(s)!"; // Mensagem informando ao jogador que ele ganhou moedas
    }
}
