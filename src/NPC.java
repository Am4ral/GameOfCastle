import java.util.HashMap;
import java.util.Random;

/**
 * Classe NPC - um personagem interativo no jogo Game Of Castle
 * 
 * Essa classe é parte da aplicação "Game Of Castle".
 * O NPC representa o personagem com o qual o usuário pode interagir durante
 * o jogo, resolvendo enigmas.
 * 
 * Em diferentes contextos, cada NPC apresenta enigmas que devem ser respondidos
 * e que modificam o rumo da história.
 * 
 * @author Matheus Bertoldo
 * @version 20.11.2023
 */
public class NPC {

    private String nome;
    private String descricao;
    HashMap<String, String> enigmas;

    /**
     * Instancia o NPC a partir de nome e descrição e estabele os
     * enigmas que serão utilizados.
     * 
     * @param nome      O nome do NPC.
     * @param descricao A descrição do NPC.
     */
    public NPC(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        enigmas = new HashMap<>();

        if (nome.equals("Merschmann")) {
            adicionarEnigmasPPOO();
        } else {
            adicionarEnigmas();
        }
    }

    /**
     * Adiciona os enigmas genéricos de um NPC à lista de enigmas.
     */
    private void adicionarEnigmas() {
        enigmas.put("lagrima", "\"É clara e salgada, cabe em um olho e pesa uma tonelada?\"");
        enigmas.put("porta", "\"O que dá o poder de atravessar paredes?\"");
        enigmas.put("gelo", "\"O que não se pode queimar no fogo nem afundar na água?\"");
        enigmas.put("fogo", "\"O que precisa ser alimentado para viver, mas morre se recebe água?\"");
        enigmas.put("ancora",
                "\"O que quando você precisa, você joga fora e, quando não precisa mais, pega de volta?\"");
        enigmas.put("segredo", "\"O que quando você tem, quer compartilhar, mas se compartilha, não tem mais?\"");
    }

    /**
     * Adiciona os enigmas específicos de PPOO à lista de enigmas.
     */
    private void adicionarEnigmasPPOO() {
        enigmas.put("private", "\"Qual deve ser a visibilidade dos atributos de uma classe?\"");
        enigmas.put("extends", "\"Qual a palavra utilizada para indicar que uma classe é subclasse de outra?\"");
        enigmas.put("encapsulamento",
                "\"Qual é o termo que descreve a prática de esconder a implementação interna \nde um objeto e restringir o acesso aos detalhes internos?\"");
        enigmas.put("interface",
                "\"Que elemento em programação orientada a objetos define um conjunto de métodos que \numa classe deve implementar, sem especificar a implementação real desses métodos?\"");
        enigmas.put("construtor",
                "\"Que método especial em uma classe é responsável por inicializar os atributos quando \num objeto é criado?\"");
        enigmas.put("final", "\"Qual a palavra-chave que define um atributo como constante?\"");
    }

    /**
     * @return O nome do NPC.
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return A descrição do NPC.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @return Um enigmas aleatório presente na lista de enigmas.
     */
    public String getEnigmaAleatorio() {

        Random rand = new Random();
        int indexEnigma = rand.nextInt(enigmas.size());

        int cont = 0;
        for (String resposta : enigmas.keySet()) {
            if (indexEnigma == cont) {
                return enigmas.get(resposta);
            }
            cont++;
        }

        return null;
    }

    /**
     * Define a partir do enigma, se a resposta enviada está correta.
     * 
     * @param resposta A resposta do enigma.
     * @param enigma   O enigma.
     * @return true se a resposta está certa caso contrário false.
     */
    public boolean acertouEnigma(String resposta, String enigma) {
        String eng = enigmas.get(resposta);

        if (enigma.equals(eng)) {
            return true;
        }
        return false;

    }

}