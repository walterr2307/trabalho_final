import java.util.ArrayList;

public class CasaSorte extends Casa {

    // Método que aplica a regra da Casa da Sorte
    // Faz o jogador avançar 3 casas, exceto se o tipo dele for "azarado"
    public void aplicarRegra(int indice, Jogador jog, ArrayList<Jogador> jogs) {
        if (!jog.getTipo().equals("azarado")) // Verifica se o jogador não é "azarado"
            jog.andarCasas(3); // O jogador avança 3 casas se não for azarado
    }

    // Método que retorna uma mensagem dependendo do tipo de jogador
    public String getMsg(Jogador jog) {
        if (jog.getTipo().equals("azarado")) // Se o jogador for azarado
            return "Voce parou na casa da sorte!"; // Mensagem neutra para jogadores azarados
        else
            return "Voce andou 3 casas!"; // Mensagem informando que o jogador avançou 3 casas
    }
}
