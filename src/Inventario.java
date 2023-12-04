import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Inventario {
    private ArrayList<Item> itens;

    /**
     * Instancia o inventário responsável por armazenar itens.
     */
    public Inventario() {
        itens = new ArrayList<>();
    }

    /**
     * 
     * @return Uma lista não modificável dos itens do inventário.
     */
    public List<Item> getItens() {
        return Collections.unmodifiableList(itens);
    }

    /**
     * Cria e adiciona um item ao inventário a partir do nome e descrição do mesmo.
     * 
     * @param nome      O nome do item.
     * @param descricao A descrição do item.
     */
    public void adicionarItem(String nome, String descricao) {
        itens.add(new Item(nome, descricao));
    }

    /**
     * Busca o item correspondente ao nome fornecido.
     * 
     * @param nome O nome do item.
     * @return O item correspondente ao nome.
     */
    public Item buscarItem(String nome) {
        for (Item i : itens) {
            if (i.getNome() == nome) {
                return i;
            }
        }
        return null;
    }

    /**
     * Remove o item correspondete ao nome fornecido e dispara uma exceção caso o
     * item não esteja presente no inventário para controle durante o
     * desenvolvimento.
     * 
     * @param nome O nome do item.
     * @throws Exception Exceção disparada caso não haja um item de nome
     *                   correspondente no inventário.
     */
    public void removerItem(String nome){
        Item item = buscarItem(nome);
        itens.remove(item);
    }

    /**
     * Define se o item existe ou não no inventário a partir do nome fornecido.
     * 
     * @param nome O nome do item.
     * @return Um booleano "true" caso exista e, caso contrário, "false".
     */
    public boolean existeItem(String nome) {
        Item item = buscarItem(nome);
        return itens.contains(item);
    }
}
