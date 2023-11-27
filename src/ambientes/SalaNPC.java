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
}
