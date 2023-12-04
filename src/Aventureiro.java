import java.util.List;

/**
 * Classe Aventureiro - o personagem jogável do jogo Game Of Castle
 * 
 * Essa classe é parte da aplicação "Game Of Castle".
 * O aventureiro representa o personagem com o qual o usuário/jogador joga. Ele
 * possui pontos de vida, nome e uma arma com a qual enfrentará seus inimigos.
 * 
 * O aventureiro é suscetível a danos e a opções de cura durante o jogo, além de
 * receber itens que são importantes para concluir sua jornada.
 * 
 * @author Matheus Bertoldo, João Pedro Ramalho, Renan Ribeiro Pereira.
 * @version 01.12.2023
 */

public class Aventureiro {

    private int pontosDeVida;
    private String arma;
    private Inventario inventario;
    private String nome;

    /**
     * Instancia o aventureiro com nome e arma escolhida. Inicialmente, começa com 100
     * pontos de vida e duas chaves em seu inventário.
     * 
     * @param nome O nome do aventureiro.
     * @param arma A arma do aventureiro.
     */
    public Aventureiro(String nome) {
        this.nome = nome;
        pontosDeVida = 100;
        inventario = new Inventario();
    }

    /**
     * @return Os pontos de vida do aventureiro.
     */
    public int getPontosDeVida() {
        return pontosDeVida;
    }

    /**
     * Define a arma do aventureiro.
     * @param arma A arma do aventureiro.
     */
    public void setArma(String arma) {
        this.arma = arma;
    }

    /**
     * @return A arma do aventureiro
     */
    public String getArma() {
        return arma;
    }

    /**
     * @return O nome do aventureiro.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Aplica o dano, reduzindo da vida do aventureiro.
     * 
     * @param dano O dano causado ao aventureiro.
     * @return A narrativa para interface.
     */
    public String recebeDano(int dano) {
        pontosDeVida -= dano;
        return " Você perdeu " + dano + " de vida.";
    }

    /**
     * Restaura a vida do aventureiro para 100 pontos.
     * 
     * @return A narrativa para interface.
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
     * 
     * @return A narrativa para interface.
     */
    public String adicionarItem(String nomeItem, String descricao) {
        inventario.adicionarItem(nomeItem, descricao);
        return "\n\n" + nomeItem + " foi adicionado(a) ao inventário!";
    }

    /**
     * @return Os itens presentes no inventário do aventureiro.
     */
    public List<Item> getItens() {
        return inventario.getItens();
    }

    /**
     * Remove o item solicitado do inventário do aventureiro.
     * 
     * @param nome O nome do item a ser removido.
     * @return A narrativa para interface.
     */
    public String removerItem(String nome){
        inventario.removerItem(nome);
        return "\n\n" + nome + " foi removido do inventário!";
    }

    /**
     * Verifca a existência de um item no inventário do aventureiro.
     * 
     * @param nome O nome do item.
     * @return true caso o item exista caso contrário false.
     */
    public boolean existeItem(String nome) {
        return inventario.existeItem(nome);
    }
}
