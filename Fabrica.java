public class Fabrica {

    public Casa retornarCasa(String tipo_casa) {

        switch (tipo_casa) {
            case "azar":
                return new CasaAzar();
            case "jogar de novo":
                return new CasaJogarDeNovo();
            case "prisao":
                return new CasaPrisao();
            case "reversa":
                return new CasaReversa();
            case "sorte":
                return new CasaSorte();
            case "surpresa":
                return new CasaSurpresa();
            case "troca":
                return new CasaTroca();
            default:
                return new CasaSimples();
        }
    }

    public Jogador retornarJogador(String tipo_jog, String cor) {
        switch (tipo_jog) {
            case "normal":
                return new JogadorNormal(cor, tipo_jog);
            case "sortudo":
                return new JogadorSortudo(cor, tipo_jog);
            default:
                return new JogadorAzarado(cor, tipo_jog);
        }
    }
}
