package ambientes;

import entidades.NPC;

public class SalaNPC extends Ambiente{
    private NPC npc;

    public SalaNPC(String descricao, String nomeNpc, String descricaoNpc) {
        super(descricao);
        npc = new NPC(nomeNpc, descricaoNpc);
    }

    public String getNomeNpc() {
        return npc.getNome();
    }

    public String getEnigmaAleatorio(){
        return npc.getEnigmaAleatorio();
    }

    public boolean acertouEnigma(String resposta, String enigma){
        return npc.acertouEnigma(resposta, enigma);
    }

}
