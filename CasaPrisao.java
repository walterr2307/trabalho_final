import java.util.ArrayList;

public class CasaPrisao extends Casa {

    // Método que aplica a regra da Casa Prisão
    // Bloqueia a próxima jogada do jogador
    public void aplicarRegra(int indice, Jogador jog, ArrayList<Jogador> jogs) {
        jog.setVezBloqueada(true); // Define que o jogador terá sua próxima vez bloqueada
    }

    // Método que retorna uma mensagem indicando que a próxima jogada do jogador
    // será bloqueada
    public String getMsg(Jogador jog) {
        return "Proxima jogada sera bloqueada!"; // Mensagem informando ao jogador que ele não jogará na próxima vez
    }
}
