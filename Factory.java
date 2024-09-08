public class Factory {

    public Casa ajustarCasa(String tipo_casa) {
        if (tipo_casa.equals("AZAR"))
            return new CasaAzar();
        else if (tipo_casa.equals("JOGAR DE NOVO"))
            return new CasaJogarDeNovo();
        else if (tipo_casa.equals("PRISAO"))
            return new CasaPrisao();
        else if (tipo_casa.equals("REVERSA"))
            return new CasaReversa();
        else if (tipo_casa.equals("SORTE"))
            return new CasaSorte();
        else if (tipo_casa.equals("SURPRESA"))
            return new CasaSurpresa();
        else if (tipo_casa.equals("TROCA"))
            return new CasaTroca();
        else
            return new CasaSimples();
    }

    public Jogador ajustarJogador(String nome, String cor, int num_jog, String tipo_jog) {
        if (tipo_jog.equals("NORMAL"))
            return new JogadorNormal(nome, cor, num_jog, 0);
        else if (tipo_jog.equals("SORTUDO"))
            return new JogadorSortudo(nome, cor, num_jog, 1);
        else
            return new JogadorAzarado(nome, cor, num_jog, 2);
    }
}
