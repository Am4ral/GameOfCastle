package jogador;
import java.util.List;

/**
 * Classe jogador.Aventureiro - um personagem do jogo Game Of Castle
 * 
 * Essa classe é parte da aplicacao "Castle Of Castle".
 * O aventureiro representa o personagem com o qual o usuário/jogador joga. Ele
 * possui pontos de vida, nome e uma arma com a qual enfrentará seus inimigos.
 * 
 * O aventureiro é suscetível a danos e a opções de cura durante o jogo, além de
 * receber itens que são importantes para concluir sua jornada.
 * 
 * @author Matheus Bertoldo
 * @version 20.11.2023
 */

public class Aventureiro {

    private int pontosDeVida;
    private String arma;
    private Inventario inventario;
    private String nome;

    /**
     * Cria o aventureiro com nome e arma escolhida. Inicialmente, começa com 100
     * pontos de vida e duas chaves em seu inventário.
     * 
     * @param nome O nome do aventureiro.
     */
    public Aventureiro(String nome) {
        this.nome = nome;
        pontosDeVida = 100;
        inventario = new Inventario();
    }

    /**
     * 
     * @return Os pontos de vida do aventureiro.
     */
    public int getPontosDeVida() {
        return pontosDeVida;
    }

    public void setArma(String arma) {
        this.arma = arma;
    }

    /**
     * 
     * @return A arma do aventureiro
     */
    public String getArma() {
        return arma;
    }

    /**
     * 
     * @return String representando o nome do aventureiro
     */
    public String getNome() {
        return nome;
    }

    /**
     * 
     * @param dano O dano causado ao aventureiro.
     */
    public String recebeDano(int dano) {
        pontosDeVida -= dano;
        return " Você perdeu " + dano + " de vida.";
    }

    /**
     * Restaura a vida do aventureiro para 100 pontos.
     */
    public String curarVida() {
        pontosDeVida = 100;
        return "\nVocê curou todos seus pontos de vida!";
    }

    /**
     * Cria o item e adiciona ao inventário do jogador.
     * 
     * @param nomeItem      O nome do item.
     * @param descricao A descrição do item.
     */

    public String adicionarItem(String nomeItem, String descricao) {
        inventario.adicionarItem(nomeItem, descricao);
        return "\n\n" + nomeItem + " foi adicionado(a) ao inventário!";
    }

    public List<Item> getItens() {
        return inventario.getItens();
    }

    public String removerItem(String nome){
        inventario.removerItem(nome);
        return "\n\n" + nome + " foi removido do inventário!";
    }

    public boolean existeItem(String nome) {
        return inventario.existeItem(nome);
    }
}
