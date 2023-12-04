/**
 * Classe SalaItemPorta - um ambiente que tem uma porta e um item.
 *
 * Esta classe eh parte da aplicacao "Game Of Castle".
 * "Game Of Castle" eh um jogo de aventura muito simples, baseado em texto.
 *
 * Uma "SalaDano" representa uma localizacao no cenario do jogo que causa
 * possui uma porta e um item dento dela.
 * 
 * @author Marco Túlio
 */

public class SalaItemPorta extends Ambiente {
    private String item;
    private boolean trancado;

    /**
     * Cria uma SalaItemPorta com a "descricao" e o item que está dentro
     * dela. Inicialmente, ele nao tem saidas. "descricao" eh algo
     * como "uma cozinha" ou "um jardim aberto".
     * 
     * @param descricao A descricao do ambiente.
     * @param item      item que está dentro da sala.
     */
    public SalaItemPorta(String descricao, String item) {
        super(descricao);
        this.item = item;
        trancado = true;
    }

    /**
     * Cria uma SalaItemPorta com a "descrição" e o item que está dentro
     * dela. Inicialmente, ele nao tem saidas. "descrição" eh algo
     * como "uma cozinha" ou "um jardim aberto".
     * Nesse caso, podemos informar que porta esta trancada pelo parametro
     * trancado.
     * 
     * @param descricao A descrição do ambiente.
     * @param item      item que está dentro da sala.
     * @param trancado  se a porta esta trancada.
     */
    public SalaItemPorta(String descricao, String item, boolean trancado) {
        super(descricao);
        this.item = item;
        this.trancado = trancado;
    }

    /**
     * Retorna o item que esta na sala
     * 
     * @return uma String contendo o nome do item que está na sala
     */
    public String getItem() {
        return item;
    }

    /**
     * Retorna se a sala esta trancada
     * 
     * @return um boolean, true para trancado, false para aberto
     */
    public boolean isTrancado() {
        return trancado;
    }

    /**
     * Coloca a sala como trancada.
     * 
     * @param boolean informando se a sala está trancada ou não
     */
    public void setTrancado(boolean trancado) {
        this.trancado = trancado;
    }
}