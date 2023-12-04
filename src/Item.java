/**
* Classe Item - um item presente no jogo Game of Castle.
 * 
 * Essa classe é parte da aplicação "Game Of Castle".
 * O item representa um item presente dentro no jogo que pode ser coletado e adicionado
 * ao inventário do aventureiro durante sua jornada.
 * 
 * @author Matheus Bertoldo
 * @version 20.11.2023
 */
public class Item {

    private String nome;
    private String descricao;

    /**
     * Instancia um item a partir do nome e descrição fornecido.
     * 
     * @param nome      O nome do item.
     * @param descricao A descricao do item.
     */
    public Item(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    /**
     * @return O nome do item.
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return A descrição do item.
     */
    public String getDescricao() {
        return descricao;
    }

}
