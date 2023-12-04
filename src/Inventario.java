import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe Inventario - um sistema de armazenamento de itens do aventureiro.
 * 
 * Essa classe é parte da aplicação "Game Of Castle".
 * O inventário representa o armazenamento de itens do aventureiro durante o jogo.
 * 
 * No inventário, podem ser adicionados, encontrados e removidos itens. Além de
 * ser permitido o clone da mesma para acesso, não interferindo no conteúdo original.
 * 
 * @author Matheus Bertoldo
 * @version 20.11.2023
 */
public class Inventario {

    private ArrayList<Item> itens;

    /**
     * Instancia um inventário.
     */
    public Inventario() {
        itens = new ArrayList<>();
    }

    /**
     * @return Uma lista não modificável dos itens presentes no inventário.
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
     * Remove o item correspondente ao nome fornecido.
     * 
     * @param nome O nome do item a ser removido.
     */
    public void removerItem(String nome) {
        Item item = buscarItem(nome);
        itens.remove(item);
    }

    /**
     * Define se o item existe ou não no inventário a partir do nome fornecido.
     * 
     * @param nome O nome do item.
     * @return true caso o item exista caso contrário false.
     */
    public boolean existeItem(String nome) {
        Item item = buscarItem(nome);
        return itens.contains(item);
    }
}
