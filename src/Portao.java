public class Portao extends Ambiente{
    private boolean trancado;

    public Portao(String descricao){
        super(descricao);
        trancado = true;
    }

    public boolean estaTrancado(){
        return trancado;
    }

    public void destrancarPortao(){
        trancado = false;
    }

    public void trancarPortao(){
        trancado = true;
    }


}
