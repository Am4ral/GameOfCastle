package ambientes;

import java.util.HashMap;

/**
 * Classe ambientes.Ambiente - um ambiente em um jogo adventure.
 *
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.
 *
 * Um "ambientes.Ambiente" representa uma localizacao no cenario do jogo. Ele eh
 * conectado aos outros ambientes atraves de saidas. As saidas sao
 * nomeadas como norte, sul, leste e oeste. Para cada direcao, o ambiente
 * guarda uma referencia para o ambiente vizinho, ou null se nao ha
 * saida naquela direcao.
 * 
 * @author Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */

public class Ambiente {
    private String descricao;
    private HashMap<String, Ambiente> saidas; // armazena as saídas do ambiente
    private boolean visitado;

    /**
     * Cria um ambiente com a "descricao" passada. Inicialmente, ele
     * nao tem saidas. "descricao" eh algo como "uma cozinha" ou "um jardim aberto".
     * 
     * @param descricao A descricao do ambiente.
     */
    public Ambiente(String descricao) {
        this.descricao = descricao;
        saidas = new HashMap<>();
        visitado = false;
    }

    /**
     * Define a direção da saída do ambiente.
     * 
     * @param direcao A direção da saída.
     * @param vizinho O ambiente para o qual a saída leva.
     */
    public void ajustarSaida(String direcao, Ambiente vizinho) {
        saidas.put(direcao, vizinho);
    }

    /**
     * @return A descrição curta do ambiente.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna a descrição do ambiente na forma:
     * Você está na entrada.
     * Saídas: Sala1 Sala2 Sala3
     * 
     * @return Uma descrição longa do ambiente.
     */
    public String getDescricaoLonga() {
        return "Você está " + descricao + ".\n" + direcoesDeSaida();
    }

    /**
     * Retorna uma String descrevendo as saídas da sala.
     * Saídas: Sala1 Sala2 Sala3
     * 
     * @return Detalhes das saídas do ambiente.
     */
    protected String direcoesDeSaida() {
        String textoSaidas = "Locais: ";
        for (String direcao : saidas.keySet()) {
            textoSaidas = textoSaidas + direcao + " ";
        }
        return textoSaidas;
    }

    public HashMap<String, Ambiente> getSaidas(){
        
        HashMap<String, Ambiente> ambientes = new HashMap<>();
        
        for(String direcao : saidas.keySet()){
            ambientes.put(direcao, saidas.get(direcao));
        }
        return ambientes;
    }

    /**
     * Retorna o ambiente que é alcançado se formos de um ambiente na
     * direção "direcao". Se há um ambiente sem direção, é retornado nulo.
     * 
     * @param direcao A direção da saída.
     * @return O ambiente presente na direção.
     */
    public Ambiente getSaida(String direcao) {
        return saidas.get(direcao);
    }

    public void visitar(){
        visitado = true;
    }

    public boolean getVisitado(){
        return visitado;
    }
}
