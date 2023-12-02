package ambientes;

import entidades.NPC;

public class SalaNPC extends Ambiente{
    private NPC npc;

    public SalaNPC(String descricao, NPC npc) {
        super(descricao);
        this.npc = npc;
    }

    public NPC getNpc() {
        return npc;
    }

    public void setNpc(NPC npc) {
        this.npc = npc;
    }

    public String getEnigmaAleatorio(){
        return npc.getEnigmaAleatorio();
    }

    public boolean acertouEnigma(String resposta, String enigma){
        return npc.acertouEnigma(resposta, enigma);
    }

}
