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
        System.out.println("\nOs indices das casas variam de 1 a 40!");
    }

    public void tipoCasaInvalido(){
        System.out.println("\nEscolha um tipo de casa entre 1 e 7!");
    }

    public void casaJaSelecionada(){
        System.out.println("\nEssa casa ja foi selecionada. Escolha outra!");
    }
}
