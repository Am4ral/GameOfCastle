import ambientes.Ambiente;
import ambientes.SalaItemPorta;
// import ambientes.SalaNPC;
// import ambientes.dano.SalaInimigo;
// import entidades.NPC;
// import ambientes.Ambiente;
// import ambientes.SalaItemPorta;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Essa eh a classe principal da aplicacao "World of Zull".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.
 * Usuarios podem caminhar em um cenario. E eh tudo! Ele realmente
 * precisa ser estendido para fazer algo interessante!
 * 
 * Para jogar esse jogo, crie uma instancia dessa classe e chame o metodo
 * "jogar".
 * 
 * Essa classe principal cria e inicializa todas as outras: ela cria os
 * ambientes, cria o analisador e comeca o jogo. Ela também avalia e
 * executa os comandos que o analisador retorna.
 * 
 * @author Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */

public class Jogo {

    private Analisador analisador;
    private Ambiente salaAtual;
    // private final List<String> itensFase1 = new ArrayList<>();
    private final HashMap<String, String> armasItens;
    private Aventureiro aventureiro;
    // private String[] armas = { "Espada", "Estilingue", "Revólver", "Arco" };

    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo() {
        analisador = new Analisador();
        armasItens = new HashMap<>();

        armasItens.put("Espada", "Pedra de amolar");
        armasItens.put("Estilingue", "Saquinho com pedras");
        armasItens.put("Revólver", "Cartucho de munições");
        armasItens.put("Arco", "Aljava com flechas");

        criarCenarioFase1();
    }

    /**
     * Cria todos os ambientes e liga as saidas deles
     */
    private void criarCenarioFase1() {
        // Ambientes Fase 1
        Ambiente salaEntrada;
        SalaItemPorta sala1, sala2, sala3, sala4;

        // cria os ambientes
        salaEntrada = new Ambiente("no salão de entrada do castelo.");
        // Collections.shuffle(itensFase1);
        List<String> itensFase1 = aleatorizarItens();

        sala1 = new SalaItemPorta("uma sala simples", itensFase1.get(0));
        sala2 = new SalaItemPorta("uma sala simples", itensFase1.get(1));
        sala3 = new SalaItemPorta("uma sala simples", itensFase1.get(2));
        sala4 = new SalaItemPorta("uma sala simples", itensFase1.get(3));

        // inicializa as saidas dos ambientes
        salaEntrada.ajustarSaida("Sala1", sala1);
        salaEntrada.ajustarSaida("Sala2", sala2);
        salaEntrada.ajustarSaida("Sala3", sala3);
        salaEntrada.ajustarSaida("Sala4", sala4);

        sala1.ajustarSaida("SalaEntrada", salaEntrada);
        sala2.ajustarSaida("SalaEntrada", salaEntrada);
        sala3.ajustarSaida("SalaEntrada", salaEntrada);
        sala4.ajustarSaida("SalaEntrada", salaEntrada);

        salaAtual = salaEntrada; // ambiente em que é iniciado o jogo
    }

    private void criarCenarioFase2() {

    Ambiente salao;
    salao = new Ambiente("em um salão enorme");

    Ambiente sala5, sala6, sala7, sala8;
    sala5 = new Ambiente("uma sala bem organizada");
    // sala6 = new SalaInimigo("uma sala vazia", 25, null);
    // sala7 = new SalaInimigo("uma sala vazia", 25, null);
    // sala8 = new SalaInimigo("uma sala vazia", 25, null);

    List<Ambiente> ambientes = new ArrayList<>();

    ambientes.add(sala5);
    // ambientes.add(sala6);
    // ambientes.add(sala7);
    // ambientes.add(sala8);

    Collections.shuffle(ambientes);

    // inicializa as saidas dos ambientes
    salao.ajustarSaida("Sala5", ambientes.get(0));
    salao.ajustarSaida("Sala6", ambientes.get(1));
    salao.ajustarSaida("Sala7", ambientes.get(2));
    salao.ajustarSaida("Sala8", ambientes.get(3));

    sala5.ajustarSaida("Salao", salao);
    // sala6.ajustarSaida("Salao", salao);
    // sala7.ajustarSaida("Salao", salao);
    // sala8.ajustarSaida("Salao", salao);

    salaAtual = salao;
    }

    /**
     * Aleatoriza a localização dos itens nas salas da primeira fase.
     * 
     * @return Lista aleatorizada de itens.
     */
    private List<String> aleatorizarItens() {

        List<String> itens = new ArrayList<>();

        for (String arma : armasItens.keySet()) {
            itens.add(armasItens.get(arma));
        }

        Collections.shuffle(itens);

        return itens;
    }

    public void personalizarAventureiro(String nome) {
        aventureiro = new Aventureiro(nome);
    }

    public String exibirMensagemArma() {
        String mensagemArmas = "Escolha o número da sua arma:\n";

        int opcao = 1;
        for (String arma : armasItens.keySet()) {
            mensagemArmas += opcao + " - " + arma;
            opcao++;
            if (opcao <= armasItens.size()) {
                mensagemArmas += "\n";
            }
        }

        return mensagemArmas;
    }

    private String escolheArma(String valorArma) {
        int posicao = -1;

        try {
            posicao = Integer.parseInt(valorArma);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("\nPor favor, insira um número!");
        }

        if (posicao < 1 || posicao > armasItens.size()) {
            throw new IndexOutOfBoundsException("\nEssa arma não existe. Por favor, digite um número válido!");
        }

        String armaEscolhida = "";
        int opcao = 1;
        for (String arma : armasItens.keySet()) {
            if (posicao == opcao) {
                armaEscolhida = arma;
                break;
            }
            opcao++;
        }

        return armaEscolhida;
    }

    /**
     * Rotina principal do jogo.
     * 
     * @throws Exception
     */
    public String jogarFase1(String comandoUsuario){
        boolean estaPreparado = estaPreparado(armasItens.get(aventureiro.getArma()));
        String mensagemRetorno = "";

        if (!estaPreparado && temChave()) {
            Comando comando = analisador.getComando(comandoUsuario);
            String retornoComando = processarComando(comando);
            if (!retornoComando.equals("sair")) {
                return retornoComando;
            }
        } else {
            mensagemRetorno += "\n\nBatidas violentas em madeira começam a ecoar de fora da sala. Assustado, você retorna à Sala de Entrada para averiguar o barulho."
                    + "\nAlgo está esmurrando de forma violenta as portas que bloqueam o caminho!"
                    + "\nCiente da proximidade, você se prepara para o pior."
                    + "\nA porta que antes parecia impossível de quebrar é destruída! Pedaços de madeira voam para os lados."
                    + "\nAo fundo, surge um Orc furioso que avança em sua direção.";
        }

        if (!estaPreparado) {
            mensagemRetorno += "\n\nO Orc te ataca brutalmente. Você é incapaz de se defender...";
        } else {
            mensagemRetorno += "\n\nVocê luta bravamente. Uma batalha intensa finaliza e você sai vitorioso!";
        }

        aventureiro.limparInventario();
        return mensagemRetorno;
    }

    /**
     * Verifica se o aventureiro possui o item necessário correspondente a sua arma
     * no inventário.
     * 
     * @return true se possui o item, caso contrário false.
     */
    private boolean estaPreparado(String item) {
        return aventureiro.existeItem(item);
    }

    private boolean temChave() {
        return aventureiro.existeItem("Chave simples");
    }


    /**
     * Imprime a mensagem de derrota.
     */
    public String imprimirDerrota() {
        return "\nSeus olhos fecham..."
                + "\nSua vida se esvai."
                + "\nA escuridão te consome."
                + "\nVocê está morto!";
    }

    public String mensagemFimJogo() {
        return "\nObrigado por jogar. Até mais!";
    }

    public String getComandoUsuario(String mensagem) {
        return mensagem;
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    public String imprimirBoasVindas() {
        return "Bem vindo ao Game of Castle!\nGame of Castle é um jogo aventura incrível.\nApós escolher sua arma, digite 'ajuda' se precisar de ajuda.\n\n";
    }

    public String imprimirContextoInicial() {

        return "\n\nVocê é um aventureiro e está prestes a entrar em um grande castelo."
                + "\nNa entrada, você se encontra com um ancião e recebe duas chaves."
                + "\nAlém disso, ele te passa uma dica sobre a sala que possui um item para completar sua arma: O item que você precisa não está na "
                + imprimirDica() + "."
                + "\nÀ sua frente há um enorme portão. À sua esquerda há duas portas e à direita também."
                + "\nCurioso, você se aproxima do portão maior. Não há fechadura. Você tenta abrí-lo, porém, o portão não se move."
                + " Sem sucesso, você retorna.\n" + salaAtual.getDescricaoLonga();
    }

    /**
     * Imprime uma dica. A dica consiste em qual sala o item necessário não está.
     * 
     * @return Sala em que o item não está.
     */
    private String imprimirDica() {

        String itemNecessario = armasItens.get(aventureiro.getArma());
        HashMap<String, Ambiente> salas = salaAtual.getSaidas();

        String chave = null;
        for (String a : salas.keySet()) {
            if (((SalaItemPorta) salas.get(a)).getItem().equals(itemNecessario)) {
                chave = a;
            }
        }
        salas.remove(chave);

        List<String> chaves = new ArrayList<>(salas.keySet());
        Collections.shuffle(chaves);

        return chaves.get(0);
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * 
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo, caso contrário false.
     */
    private String processarComando(Comando comando) {
        if (comando.ehDesconhecido()) {
            return "\nNão sei o que você quer dizer...";
        }

        String commandWord = comando.getPalavraDeComando();

        if (commandWord.equals("ajuda")) {
            return imprimirAjuda();
        } else if (commandWord.equals("ir")) {
            return irParaAmbiente(comando);
        } else if (commandWord.equals("sair")) {
            return "sair";
        }
        return "";
    }

    // Implementacoes dos comandos do usuario

    /**
     * Printe informacoes de ajuda.
     */
    private String imprimirAjuda() {
        return "\nSeus comandos são: " + analisador.mostrarComandos();
    }

    private String irParaAmbiente(Comando comando) {
        if (!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            return "\nIr para onde?";
        }

        String direcao = comando.getSegundaPalavra();

        // Tenta sair do ambiente atual
        Ambiente proximaSala = salaAtual.getSaida(direcao);
        String retorno = "";

        if (proximaSala == null) {
            return "\nNão há esse local!";
        } else {
            if (proximaSala instanceof SalaItemPorta) {
                retorno = interagirComSalaItemPorta((SalaItemPorta) proximaSala);
                // String municao = ((SalaItemPorta) salaAtual).getItem();

                // aventureiro.adicionarItem(municao,
                // "Objeto que completa uma arma específica.");

                // retorno += "\n\nVocê está em " + salaAtual.getDescricao() + ".\n" + "Nela,
                // você encontra " + municao
                // + " e guarda." + "\n\nO item foi adicionado ao inventário!";
            } else {
                salaAtual = proximaSala;
            }

            if (!retorno.equals("\nVocê já visitou essa sala...")) {
                retorno += "\n\n" + salaAtual.getDescricaoLonga();
            }
            
            return retorno;
        }
    }

    public int getVidaAventureiro() {
        return aventureiro.getPontosDeVida();
    }

    public String getArmaAventureiro() {
        return aventureiro.getArma();
    }

    public String getNomeAventureiro() {
        return aventureiro.getNome();
    }

    public String setArmaAventureiro(String arma) {
        try {
            aventureiro.setArma(escolheArma(arma));
        } catch (IndexOutOfBoundsException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return e.getMessage();
        }
        return "";
    }

    public List<Item> getItensAventureiro() {
        return aventureiro.getItens();
    }

    /**
     * Responsável pelas interações do aventureiro com salas com itens.
     * 
     * @param sala A sala que o aventureiro entrou.
     * @return true se o aventureiro destrancou a porta, caso contrário false
     */
    private String interagirComSalaItemPorta(SalaItemPorta sala) {
        if (!sala.isTrancado()) {
            return "\nVocê já visitou essa sala...";

        }

        String municao = sala.getItem();

        aventureiro.adicionarItem(municao,
                "Objeto que completa uma arma específica.");

        aventureiro.removerItem("Chave simples");

        sala.setTrancado(false);
        salaAtual = sala;

        return "\n\nVocê está em " + salaAtual.getDescricao() + ".\n" + "Nela, você encontra um(a) " + municao
                + " e guarda." + "\n\nO item foi adicionado ao inventário!";
    }

    /**
     * "Sair" foi digitado. Verifica o resto do comando pra ver
     * se nos queremos realmente sair do jogo.
     * 
     * @return true, se este comando sai do jogo, false, caso contrario
     */
    // private boolean sair(Comando comando) {
    // if (comando.temSegundaPalavra()) {
    // System.out.println("Sair?");
    // return false;
    // } else {
    // return true; // sinaliza que nos queremos sair
    // }
    // }
}
