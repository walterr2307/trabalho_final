import java.util.ArrayList;
import java.util.Scanner;

public class CasaTroca extends Casa {
    // Scanner para capturar entrada do usuário
    Scanner scanner = new Scanner(System.in);

    // Método que aplica a regra de compra de acessórios para o jogador atual
    public void aplicarRegra(Jogador jog, ArrayList<Jogador> jogs) {
        int num_moedas, casa_atual;
        boolean comprar;

        // Pergunta ao jogador se deseja gastar 5 moedas para comprar uma roupa
        System.out.print("Deseja gastar 5 moedas para comprar uma roupa (true ou false): ");
        comprar = scanner.nextBoolean();

        // Se o jogador não quiser comprar, o método retorna sem fazer nada
        if (!comprar)
            return;

        try {
            // Verifica se o jogador tem moedas suficientes ou se já possui todos os
            // acessórios
            if (jog.getNumMoedas() < 5 || jog instanceof OculosEscuros)
                throw new AcaoInvalidaException(); // Lança exceção se a ação for inválida

            // Armazena o número de moedas e a posição atual da casa do jogador
            num_moedas = jog.getNumMoedas();
            casa_atual = jog.getCasaAtual();

            // Verifica o tipo de acessório que o jogador possui e aplica a próxima troca
            if (jog instanceof Bone)
                jog = new Moleton(jog); // Se o jogador já tem um boné, recebe um moletom
            else if (jog instanceof Moleton)
                jog = new OculosEscuros(jog); // Se o jogador já tem moletom, recebe óculos escuros
            else
                jog = new Bone(jog); // Se não tiver nenhum acessório, recebe um boné

            // Atualiza os dados do jogador (número de moedas e posição na casa)
            jog.atualizarDados(num_moedas, casa_atual);
        } catch (AcaoInvalidaException e) {
            // Trata exceções relacionadas à ação inválida
            if (jog.getNumMoedas() < 5)
                e.moedasInsuficientes(); // Se o jogador não tem moedas suficientes, notifica
            else
                e.todosAcessoriosComprados(); // Se já comprou todos os acessórios, notifica
        }
    }

    // Retorna uma mensagem personalizada quando o jogador para na casa de troca
    public String getMsg(Jogador jog) {
        return "Parou na casa de troca!";
    }
}
