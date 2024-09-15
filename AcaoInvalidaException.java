public class AcaoInvalidaException extends Exception {

    public void qtdCasasInsuficientes() {
        System.out.println("\nPonha, pelo menos, 10 casas!\n");
    }

    public void qtdJogsInvalida() {
        System.out.println("\nPonha entre 2 e 6 jogadores!\n");
    }

    public void excedeuQtdCasas() {
        System.out.println("\nO numero de casas excedeu o limite!\n");
    }

    public void tipoInvalido() {
        System.out.println("\nPonha um tipo valido!\n");
    }

    public void corInvalida() {
        System.out.println("\nNao eh possivel colocar essa cor!\n");
    }

    public void tiposIguais() {
        System.out.println("\nOs tipos de todos os jogadores nao podem serem iguais!\n");
    }

    public void cartaInvalida() {
        System.out.println("\nSeleciona um indice entre 1 e 2!\n");
    }

    public void moedasInsuficientes() {
        System.out.println("\nVoce nao tem moedas suficientes!\n");
    }

    public void todosAcessorios() {
        System.out.println("\nVoce ja comprou todos os acessorios!\n");
    }
}
