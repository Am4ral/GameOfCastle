package ambientes;

import java.util.ArrayList;

public class SalaItemPorta extends Ambiente{
    private String item;
    private boolean trancado;

    public SalaItemPorta(String descricao, String item) {
        super(descricao);
        this.item = item;
        trancado = false;
    }

    public SalaItemPorta(String descricao, String item, boolean trancado) {
        super(descricao);
        this.item = item;
        this.trancado = trancado;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public boolean isTrancado() {
        return trancado;
    }

    public void setTrancado(boolean trancado) {
        this.trancado = trancado;
    }

    @Override
    public String getDescricaoLonga(){
        return "Você está em uma sala vazia, num canto tem " + item + ".\n" + direcoesDeSaida();
    }
}

