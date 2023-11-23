package ambientes.dano;

import java.util.ArrayList;

public class SalaInimigo extends SalaDano{

    private ArrayList<String> descricaoAcao;

    public SalaInimigo(String descricao, int danoMaximo, ArrayList<String> descricaoAcao) {
        super(descricao, danoMaximo);
        this.descricaoAcao = descricaoAcao;
    }

    public ArrayList<String> getDescricaoAcao() {
        return descricaoAcao;
    }

    public void setDescricaoAcao(ArrayList<String> descricaoAcao) {
        this.descricaoAcao = descricaoAcao;
    }
}
