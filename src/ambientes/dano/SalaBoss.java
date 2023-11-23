package ambientes.dano;

public class SalaBoss extends SalaDano{
    private String dialogo;

    public SalaBoss(String descricao, int danoMaximo, String dialogo) {
        super(descricao, danoMaximo);
        this.dialogo = dialogo;
    }

    public String getDialogo() {
        return dialogo;
    }

    public void setDialogo(String dialogo) {
        this.dialogo = dialogo;
    }
}
