import java.util.ArrayList;
import java.util.Scanner;

public class CasaTroca extends Casa {
    Scanner scanner = new Scanner(System.in);

    public void aplicarRegra(Jogador jog, ArrayList<Jogador> jogs) {
        boolean comprar;

        System.out.print("Deseja gastar 5 moedas para comprar uma roupa (true ou false): ");
        comprar = scanner.nextBoolean();

        if (comprar && jog.getNumMoedas() < 5) {
            comprar = false;
            System.out.println("\nVoce nao tem 5 moedas!");
        }

        if(comprar){
            
        }
    }

    public String getMsg(Jogador jog) {
        return "Parou na casa de troca!";
    }
}
