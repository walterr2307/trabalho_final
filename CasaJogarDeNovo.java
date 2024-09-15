import java.util.ArrayList;

public class CasaJogarDeNovo extends Casa {

    // Método que aplica a regra da Casa Jogar De Novo
    // Define que o jogador poderá jogar novamente
    public void aplicarRegra(int indice, Jogador jog, ArrayList<Jogador> jogs) {
        jog.setJogarNovamente(true); // Permite ao jogador jogar novamente definindo o flag jogar_novamente como true
    }

    // Método que retorna a mensagem indicando que o jogador pode jogar novamente
    public String getMsg(Jogador jog) {
        return "Jogue novamente!"; // Mensagem informando que o jogador tem direito a outra jogada
    }
}
