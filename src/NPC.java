import java.util.HashMap;
import java.util.Random;

public class NPC {
    
    private String nome;
    private String descricao;
    HashMap<String, String> enigmas;
    
    public NPC(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        enigmas = new HashMap<>();
        
        if(nome.equals("Merschmann")){
            adicionarEnigmasPPOO();
        } else {
            adicionarEnigmas();
        }
    }

    private void adicionarEnigmas(){
        enigmas.put("lagrima", "\"É clara e salgada, cabe em um olho e pesa uma tonelada?\"");
        enigmas.put("porta", "\"O que dá o poder de atravessar paredes?\"");
        enigmas.put("gelo", "\"O que não se pode queimar no fogo nem afundar na água?\"");
        enigmas.put("fogo", "\"O que precisa ser alimentado para viver, mas morre se recebe água?\"");
        enigmas.put("ancora", "\"O que quando você precisa, você joga fora e, quando não precisa mais, pega de volta?\"");
        enigmas.put("segredo", "\"O que quando você tem, quer compartilhar, mas se compartilha, não tem mais?\"");
    }

    private void adicionarEnigmasPPOO(){
        enigmas.put("private", "\"Qual deve ser a visibilidade dos atributos de uma classe?\"");
        enigmas.put("extends", "\"Qual a palavra utilizada para indicar que uma classe é subclasse de outra?\"");
        enigmas.put("encapsulamento", "\"Qual é o termo que descreve a prática de esconder a implementação interna \nde um objeto e restringir o acesso aos detalhes internos?\"");
        enigmas.put("interface", "\"Que elemento em programação orientada a objetos define um conjunto de métodos que \numa classe deve implementar, sem especificar a implementação real desses métodos?\"");
        enigmas.put("construtor", "\"Que método especial em uma classe é responsável por inicializar os atributos quando \num objeto é criado?\"");
        enigmas.put("final", "\"Qual a palavra-chave que define um atributo como constante?\"");
    }

    public String getNome() {
        return nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public String getEnigmaAleatorio(){

        Random rand = new Random();
        int indexEnigma = rand.nextInt(enigmas.size());

        int cont = 0;
        for(String resposta : enigmas.keySet()){
            if(indexEnigma == cont){
                return enigmas.get(resposta);
            }
        }

        return null;
    }
    public boolean acertouEnigma(String resposta, String enigma){
        String eng = enigmas.get(resposta);
        
        if(enigma.equals(eng)){
            return true;
        }
        return false;

    }

}
