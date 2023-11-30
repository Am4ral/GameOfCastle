package entidades;

import java.util.HashMap;
import java.util.Random;

public class NPC {
    
    private String nome;
    private String descricao;
    HashMap<String, String> enigmas;
    
    private static Random rand;

    public NPC(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        enigmas = new HashMap<>();
        adicionarEnigmas();

        rand = new Random();
    }

    private void adicionarEnigmas(){
        enigmas.put("lagrima", "\"É clara e salgada, cabe em um olho e pesa uma tonelada?\"");
    }

    public String getNome() {
        return nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public String getEnigmaAleatorio(){
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

