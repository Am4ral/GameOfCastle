
import java.util.Random;

/**
 * Classe SalaDano - um ambiente que causa dano ao jogador.
 *
 * Esta classe eh parte da aplicacao "Game Of Castle".
 * "Game Of Castle" eh um jogo de aventura muito simples, baseado em texto.
 *
 * Uma "SalaDano" representa uma localizacao no cenario do jogo que causa
 * dano ao jogador.
 * 
 * @author Marco Túlio, Matheus
 */

public class SalaDano extends Ambiente {

    private static Random rand = new Random();
    private int dano;

    /**
     * Cria uma SalaDano com a "descrição" e o dano máximo que pode ser causado
     * por ela. Inicialmente, ele nao tem saidas. "descrição" eh algo
     * como "uma cozinha" ou "um jardim aberto".
     * 
     * @param descricao  A descrição do ambiente.
     * @param danoMaximo Dano máximo que esse ambinete pode causar.
     */
    public SalaDano(String descricao, int danoMaximo) {
        super(descricao);
        this.dano = rand.nextInt(danoMaximo) + 1;
    }

    /**
     * Retorna o dano que essa sala causa
     * 
     * @return um int contendo o dano causado pela sala
     */
    public int getDano() {
        return dano;
    }

}
