package entidades;

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
    }

    private void adicionarEnigmasPPOO(){
        enigmas.put("privada", "\"Qual deve ser a visibilidade dos atributos de uma classe\"");
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

