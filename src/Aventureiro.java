/**
 * Classe Aventureiro - um personagem do jogo Game Of Castle
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
    private String nome;
    private String arma;
    private Inventario inventario;

    /**
     * Cria o aventureiro com nome e arma escolhida. Inicialmente, começa com 100
     * pontos de vida e duas chaves em seu inventário.
     * 
     * @param nome O nome do aventureiro.
     * @param arma A arma do aventureiro.
     */
    public Aventureiro(String nome, String arma) {
        pontosDeVida = 100;
        this.nome = nome;
        this.arma = arma;
        inventario = new Inventario();
        receberChaves();
    }

    /**
     * 
     * @return Os pontos de vida do aventureiro.
     */
    public int getPontosDeVida() {
        return pontosDeVida;
    }

    /**
     * 
     * @return O nome do aventureiro.
     */
    public String getNome() {
        return nome;
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
     * @param dano O dano causado ao aventureiro.
     */
    public void recebeDano(int dano) {
        pontosDeVida -= dano;
    }

    /**
     * Restaura a vida do aventureiro para 100 pontos.
     */
    public void curarVida() {
        pontosDeVida = 100;
    }

    /**
     * Cria o item e adiciona ao inventário do jogador.
     * 
     * @param nome O nome do item.
     * @param descricao A descrição do item.
     */
    public void adicionarItem(String nome, String descricao) {
        inventario.adicionarItem(nome, descricao);
    }

    /**
     * Adiciona as chaves fornecidas no início do jogo ao inventário.
     */
    private void receberChaves() {
        adicionarItem("Chave simples", "Uma chave simples feita de metal.");
        adicionarItem("Chave simples", "Uma chave simples feita de metal.");
    }

}