public class SalaItemPorta extends Ambiente {
    private String item;
    private boolean trancado;

    public SalaItemPorta(String descricao, String item) {
        super(descricao);
        this.item = item;
        trancado = true;
    }

    public SalaItemPorta(String descricao, String item, boolean trancado) {
        super(descricao);
        this.item = item;
        this.trancado = trancado;
    }

    public String getItem() {
        return item;
    }

    public boolean isTrancado() {
        return trancado;
    }

    public void setTrancado(boolean trancado) {
        this.trancado = trancado;
    }

}

