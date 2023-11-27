package ambientes.dano;

import ambientes.Ambiente;

import java.util.Random;

public class SalaDano extends Ambiente {
    private static Random rand = new Random();
    private int dano;

    public SalaDano(String descricao, int danoMaximo) {
        super(descricao);
        this.dano = rand.nextInt(danoMaximo)+1;
    }
}
