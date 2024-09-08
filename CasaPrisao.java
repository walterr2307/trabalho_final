import java.util.ArrayList;
import java.util.Scanner;

public class CasaPrisao extends Casa {
    private Scanner scanner = new Scanner(System.in);

    public void aplicarRegra(Jogador jog, ArrayList<Jogador> jogs) {
        boolean gastar_moedas;

        System.out.print("\nDeseja gastar 2 moedas para fugir da prisao (true ou false): ");
        gastar_moedas = scanner.nextBoolean();

        if (gastar_moedas && jog.getNumMoedas() < 2) {
            System.out.println("\nMoedas insuficientes!\n");
            gastar_moedas = false;
        }

        if (gastar_moedas) {
            jog.setVezBloqueada(true);
            jog.addMoedas(-2);
        }

    }

    public String getMsg(Jogador jog) {
        return "A jogada foi bloqueada!";
    }
}
