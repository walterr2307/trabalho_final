import java.util.ArrayList;
import java.util.Scanner;

public class CasaTroca extends Casa {
    private Scanner scanner = new Scanner(System.in); // Scanner para capturar a entrada do usuário

    // Método que aplica a regra da Casa Troca
    // Permite ao jogador gastar 3 moedas para comprar um acessório, se possível
    public void aplicarRegra(int indice, Jogador jog, ArrayList<Jogador> jogs) {
        boolean gastar = false;

        // Loop para garantir que o jogador forneça uma entrada válida (true/false)
        while (true) {
            System.out.print("Quer gastar 3 moedas para comprar um acessório? (true/false): ");
            String input = scanner.nextLine().toLowerCase(); // Lê a entrada do jogador e converte para minúsculas

            if (input.equals("true") || input.equals("false")) {
                gastar = Boolean.parseBoolean(input); // Converte a string para um valor booleano
                break; // Sai do loop se a entrada for válida
            } else {
                System.out.println("Por favor, digite 'true' ou 'false'.");
            }
        }

        // Se o jogador não quiser gastar moedas, sai do método sem fazer alterações
        if (!gastar)
            return;

        try {
            // Verifica se o jogador já tem o acessório "Óculos Escuros" ou se não tem
            // moedas suficientes
            if (jog instanceof OculosEscuros || jog.getQtdMoedas() < 3)
                throw new AcaoInvalidaException(); // Lança exceção se a compra for inválida

            jog.addMoedas(-3); // Subtrai 3 moedas do jogador

            // Troca o acessório do jogador com base no acessório que ele já possui
            if (jog instanceof Bone)
                jog = new Moleton(jog); // Se o jogador tem Boné, ganha Moleton
            else if (jog instanceof Moleton)
                jog = new OculosEscuros(jog); // Se tem Moleton, ganha Óculos Escuros
            else
                jog = new Bone(jog); // Se não tem acessório, ganha Boné

            // Atualiza a lista de jogadores com o novo estado do jogador
            jogs.set(indice, jog);
        } catch (AcaoInvalidaException e) {
            // Trata as exceções caso o jogador não tenha moedas suficientes ou já tenha
            // todos os acessórios
            if (jog.getQtdMoedas() < 3)
                e.moedasInsuficientes(); // Exibe mensagem de moedas insuficientes
            else
                e.todosAcessorios(); // Exibe mensagem se o jogador já possui todos os acessórios

            System.out.print("Pressione ENTER para continuar: ");
            scanner.nextLine();
        }
    }

    // Método que retorna a mensagem quando o jogador para na Casa Troca
    public String getMsg(Jogador jog) {
        return "Voce parou na loja de acessorios!"; // Mensagem indicando que o jogador chegou à loja de acessórios
    }
}
