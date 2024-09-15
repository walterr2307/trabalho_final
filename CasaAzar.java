import java.util.ArrayList;

public class CasaAzar extends Casa {

    // Método que aplica a regra da Casa do Azar a um jogador
    // Se o jogador não for uma instância de JogadorSortudo, ele perde 3 moedas
    public void aplicarRegra(int indice, Jogador jog, ArrayList<Jogador> jogs) {
        if (!jog.getTipo().equals("sortudo")) // Verifica se o jogador não é sortudo
            jog.addMoedas(-3); // Subtrai 3 moedas do jogador
    }

    // Método que retorna uma mensagem específica dependendo do tipo de jogador
    public String getMsg(Jogador jog) {
        if (jog.getTipo().equals("sortudo")) // Se o jogador for sortudo
            return "Voce parou na casa do azar!"; // Mensagem quando o jogador é sortudo (mas não perde moedas)
        else
            return "Perdeu 3 moedas!"; // Mensagem para os demais jogadores que perdem 3 moedas
    }
}
