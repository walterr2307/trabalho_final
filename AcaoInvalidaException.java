public class AcaoInvalidaException extends Exception {

    public void QtdJogsInvalida() {
        System.out.println("\nColoque um valor entre 2 e 6!");
    }

    public void CorInexistente() {
        System.out.println("\nColoque uma cor valida!");
    }

    public void tipoNaoExistente() {
        System.out.println("\nColoque um tipo valido!");
    }

    public void posJaSelecionada() {
        System.out.println("\nVoce ja escolheu essa posicao!");
    }

    public void excedeLimiteCasas() {
        System.out.println("\nNao exceda o limite das casas!");
    }

    public void tipoCasaInvalido() {
        System.out.println("\nEssa tipo de casa nao pode ser selecionado!");
    }

    public void casaJaSelecionada() {
        System.out.println("\nEssa casa ja foi selecionada. Escolha outra!");
    }

    public void todosAcessoriosComprados() {
        System.out.println("\nVoce ja comprou todos os acessorios!");
    }

    public void moedasInsuficientes() {
        System.out.println("\nVoce nao tem 5 moedas!");
    }

    public void qtdCasasInsuficientes() {
        System.out.println("\nPonha, pelo menos, 3 casas!");
    }

    public void tiposIguais() {
        System.out.println("\nTodos os tipos de jogadores nao podem serem iguais!");
    }
}
