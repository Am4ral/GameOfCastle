/**
 * Classe SalaNPC - um ambiente que tem um NPC.
 *
 * Esta classe eh parte da aplicacao "Game Of Castle".
 * "Game Of Castle" eh um jogo de aventura muito simples, baseado em texto.
 *
 * Uma "SalaNPC" representa uma localizacao no cenario do jogo que possui um
 * NPC.
 * 
 * @author Marco Túlio, Matheus
 */

public class SalaNPC extends Ambiente {
    private NPC npc;

    /**
     * Cria uma SalaNPC com a "descrição", nome do NPC e a descrição do NPC
     * pasasdos.
     * Inicialmente, ele nao tem saidas. "descrição" eh algo como
     * "uma cozinha" ou "um jardim aberto".
     * 
     * @param descricao descricao do ambiente.
     * @param nomeNpc   nome do Npc da sala.
     * @param nomeNpc   descricao do NPC.
     */
    public SalaNPC(String descricao, String nomeNpc, String descricaoNpc) {
        super(descricao);
        npc = new NPC(nomeNpc, descricaoNpc);
    }

    /**
     * Retorna o nome do npc.
     * 
     * @return uma String com o nome do npc.
     */
    public String getNomeNpc() {
        return npc.getNome();
    }

    /**
     * Retorna o enigma que sera falado pelo npc.
     * 
     * @return uma String com o enigma do npc.
     */
    public String getEnigmaAleatorio() {
        return npc.getEnigmaAleatorio();
    }

    /**
     * Retorna se o jogador acertou a resposta do enigma.
     * 
     * @return boolena, true para correto, false para errado.
     */
    public boolean acertouEnigma(String resposta, String enigma) {
        return npc.acertouEnigma(resposta, enigma);
    }
}
