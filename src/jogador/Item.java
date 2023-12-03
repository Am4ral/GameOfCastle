package jogador;

public class Item {

    private String nome;
    private String descricao;

    /**
     * Cria um item a partir do nome e descrição fornecido.
     * 
     * @param nome      O nome do item.
     * @param descricao A descricao do item.
     */
    public Item(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    /**
     * 
     * @return O nome do item.
     */
    public String getNome() {
        return nome;
    }

    /**
     * 
     * @return A descricao do item.
     */
    public String getDescricao() {
        return descricao;
    }

}
