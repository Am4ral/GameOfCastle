import ambientes.Ambiente;
import ambientes.SalaItemPorta;
import ambientes.SalaNPC;
import ambientes.dano.SalaInimigo;
import entidades.NPC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import java.util.Scanner;

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
    private final HashMap<String, String> armasItens;

    private Aventureiro aventureiro;
    private Scanner entrada;

    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo() {

        entrada = new Scanner(System.in);
        analisador = new Analisador();
        armasItens = new HashMap<>();

        armasItens.put("Espada", "uma pedra de amolar");
        armasItens.put("Estilingue", "um saquinho com pedras");
        armasItens.put("Revólver", "um cartucho de munições");
        armasItens.put("Arco", "uma aljava com flechas");

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
        sala6 = new SalaInimigo("uma sala vazia", 25, null);
        sala7 = new SalaInimigo("uma sala vazia", 25, null);
        sala8 = new SalaInimigo("uma sala vazia", 25, null);

        List<Ambiente> ambientes = new ArrayList<>();

        ambientes.add(sala5);
        ambientes.add(sala6);
        ambientes.add(sala7);
        ambientes.add(sala8);

        Collections.shuffle(ambientes);

        // inicializa as saidas dos ambientes
        salao.ajustarSaida("Sala5", ambientes.get(0));
        salao.ajustarSaida("Sala6", ambientes.get(1));
        salao.ajustarSaida("Sala7", ambientes.get(2));
        salao.ajustarSaida("Sala8", ambientes.get(3));
        
        sala5.ajustarSaida("Salao", salao);
        sala6.ajustarSaida("Salao", salao);
        sala7.ajustarSaida("Salao", salao);
        sala8.ajustarSaida("Salao", salao);

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

    /**
     * Personaliza o aventureiro, instanciando e atribuindo uma arma.
     */
    private void personalizarAventureiro() {
        String arma = escolheArmas();
        aventureiro = new Aventureiro(arma);
    }

    /**
     * Imprime as opções de escolha de armas e solicita que o jogador escolha dentro
     * do limite das opções.
     * 
     * @return Arma que será atribuída ao aventureiro.
     */
    private String escolheArmas() {

        int opcao = 1;
        for (String arma : armasItens.keySet()) {
            System.out.println((opcao) + " - " + arma);
            opcao++;
        }

        System.out.print("\nEscolha o número da sua arma: ");

        int posicao = entrada.nextInt();

        while (true) {
            if (posicao < 1 || posicao > armasItens.size()) {
                System.out.println("Essa arma não existe. Por favor, digite um número válido.");
                System.out.print("\nEscolha o número da sua arma: ");
                posicao = entrada.nextInt();
            } else {
                opcao = 1;
                for (String arma : armasItens.keySet()) {
                    if (posicao == opcao) {
                        return arma;
                    }
                    opcao++;
                }
            }
        }

    }

    /**
     * Rotina principal do jogo.
     */
    public void jogar() {

        imprimirBoasVindas();
        personalizarAventureiro();
        imprimirContextoInicial();

        //if (jogarFase1()) {
            jogarFase2();
        //}

        System.out.println("Obrigado por jogar!");
        System.out.println(
                "Desenvolvido por: João Pedro Ramalho, Marco Túlio Amaral, Matheus Bertoldo e Renan Ribeiro");
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private void imprimirBoasVindas() {
        System.out.println("\nBem vindo ao Game of Castle!");
        System.out.println("Game of Castle é um jogo aventura incrível.");
        System.out.println("Digite 'help' se precisar de ajuda.\n");
    }

    /**
     * Imprime o contexto do jogo, incluindo a dica da localização do item
     * necessário para passar de fase.
     */
    private void imprimirContextoInicial() {
        System.out.println("\nVocê é um aventureiro e está prestes a entrar em um grande castelo.");
        System.out.println("Na entrada, você se encontra com um ancião e recebe duas chaves. Além disso, " +
                "ele te passa uma dica sobre a sala que possui um item para completar sua arma.");
        System.out.println(
                "\"Os deuses te escolheram aventureiro! Aquilo que você procura não está na " + imprimirDica() + ".\"");
        System.out.println(salaAtual.getDescricao());
        System.out.println("\nÀ sua frente há um enorme portão. A sua esquerda há duas portas e a direita também.\n" +
                "Curioso, você se aproxima do portão maior. Não há fechadura. Você tenta abrí-lo, porém, o portão não se move.\n"
                +
                "Sem sucesso, você retorna.\n");
        System.out.println(salaAtual.getDescricaoLonga());

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

    private void imprimirContextoFase2(){
        System.out.println("As portas agora estão abertas e, então, você decide avançar.");
        System.out.println("Você percorre um extenso corredor mal iluminado até alcançar um salão imenso\nque dá acesso a duas portas à esquerda e a duas portas à direita.");
        System.out.println("Ao centro, encontra-se um enorme portão feito de ferro.");
        System.out.println("Você se aproxima e percebe que o mesmo encontra-se trancado e retorna.\n");
        System.out.println(salaAtual.getDescricaoLonga());
    }

    /**
     * Rotina e controle das decisões realizadas na fase 1.
     * 
     * @return true se passou de fase, caso contrário false.
     */
    private boolean jogarFase1() {

        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate a primeira fase terminar.
        int salasAbertas = 0;
        while (!estaPreparado() && salasAbertas < 2) {
            Comando comando = analisador.getComando();
            if (processarComando(comando)) {
                salasAbertas++;
            }
        }

        if (!avancaParaFase2()) {
            System.out.println("O Orc te ataca brutalmente. Você é incapaz de se defender.");
            imprimirDerrota();
            return false;
        }

        System.out.println("\nVocê luta bravamente.");
        System.out.println("Uma batalha intensa finaliza e você sai vitorioso!\n");

        return true;
    }

    /**
     * Imprime o contexto do evento e verifica se o aventureiro possui o item
     * necessário para passar de fase, mudando a narrativa do jogo.
     * 
     * @return true se o aventureiro pode passar de fase, caso contrário false.
     */
    private boolean avancaParaFase2() {
        boolean passou = false;

        System.out.println("\nBatidas violentas em madeira começam a ecoar de fora da sala.");
        System.out.println("Assustado, você retorna a sala de entrada para averiguar o barulho.");
        System.out.println("Algo está esmurrando a porta de forma violenta as portas que bloqueam o caminho!");

        if (estaPreparado()) {
            System.out.println("\nCiente de que algo se aproxima, você se prepara para o pior.\n");

            aventureiro.removerItem(armasItens.get(aventureiro.getArma()));
            System.out.println(armasItens.get(aventureiro.getArma()) + " foi removido do inventário.\n");
            passou = true;
        }

        System.out.println("A porta que antes parecia impossível de quebrar é destruída!");
        System.out.println("Pedaços de madeira voam para os lados.");
        System.out.println("Ao fundo, surge um Orc furioso que avança em sua direção.");

        return passou;
    }

    /**
     * Verifica se o aventureiro possui o item necessário correspondente a sua arma
     * no inventário.
     * 
     * @return true se possui o item, caso contrário false.
     */
    private boolean estaPreparado() {
        return aventureiro.existeItem(armasItens.get(aventureiro.getArma()));
    }

    private void jogarFase2() {
        criarCenarioFase2();
        imprimirContextoFase2();

        boolean naoTerminou = false;
        while (!naoTerminou) {
            Comando comando = analisador.getComando();
            naoTerminou = processarComando(comando);
        }
        
        // sai do loop se morrer na sala, se morrer no enigma ou se passar

        if(!aventureiro.existeItem("Chave do Grande Portao")){
            imprimirDerrota();
            return;
        }


    }

    /**
     * Imprime a mensagem de derrota.
     */
    private void imprimirDerrota() {
        System.out.println("\nSeus olhos fecham...");
        System.out.println("Sua vida se esvai.");
        System.out.println("A escuridão te consome.\n");
        System.out.println("Você está morto.\n");
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * 
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo, caso contrário false.
     */
    private boolean processarComando(Comando comando) {
        boolean terminar = false;

        if (comando.ehDesconhecido()) {
            System.out.println("Não sei o que você quer dizer...");
            return false;
        }

        String commandWord = comando.getPalavraDeComando();
        if (commandWord.equals("ajuda")) {
            imprimirAjuda();
        } else if (commandWord.equals("ir")) {
            terminar = irParaAmbiente(comando);
        } else if (commandWord.equals("investigar")){
            terminar = investigar();
        }

        return terminar;
    }

    // Implementacoes dos comandos do usuario

    private boolean investigar() {
        
        boolean morreu = false;
        // Se estiver na sala com passagem secreta
        if(salaAtual.getDescricao().equals("uma sala bem organizada")){
            System.out.println("\nA sala apresentava um clima confortável destacado pela organização dos móveis.");
            System.out.println("Observando um pouco melhor, você percebe um relevo por baixo do tapete.");
            System.out.println("Curioso, você decide tirá-lo.");
            System.out.println("\nVocê encotrou uma passagem secreta!\n");
            if(interagirComPorao(salaAtual)){
                morreu = true;
            }
        } else {
            System.out.println("Não há nada de especial aqui.");
        }
    
        return morreu;
    }

    private boolean interagirComPorao(Ambiente saida) {

        NPC esfinge = new NPC("esfinge", "uma esfinge com feição séria");
        SalaNPC porao = new SalaNPC("um porão um pouco escuro", esfinge);

        porao.ajustarSaida("SalaConfortavel", saida);

        salaAtual = porao;

        System.out.println("Você desce um lance de escadas até chegar ao porão.");
        System.out.println("Você encontra" + esfinge.getDescricao() + ", e ela diz:\n");
        System.out.println("\"Aventureiro, você possui uma chance para aceitar meu enigma, caso contrário, morrerá aqui nesse porão.\"");
        System.out.println("\"Preste atenção, não repetirei novamente\"\n");

        String enigma = esfinge.getEnigmaAleatorio();
        System.out.println(enigma);
        
        entrada.nextLine();
        String resposta = entrada.nextLine();
        
        if(esfinge.acertouEnigma(resposta, enigma)){
            System.out.println("\n\"Parabéns, você acertou.\"");
            System.out.println("A esfinge some e um baú aparece.");
            System.out.println("Você abre, encontra um chave muito bem detalhada e pega.\n");

            aventureiro.adicionarItem("Chave do Grande Portao", "uma chave detalhada com entalhes em metal.");
            System.out.println(salaAtual.getDescricaoLonga());

            return false;
        }
        return true;
    }

    /**
     * Imprime informações de ajuda.
     */
    private void imprimirAjuda() {
        System.out.println("Seus comandos são:");
        analisador.mostrarComandos();
    }

    /**
     * Verifica se o comando possui destino e se ele existe. Determina as interações
     * com tipos de salas específicas.
     * 
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo, caso contrário false.
     */
    private boolean irParaAmbiente(Comando comando) {

        if (!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            System.out.println("Ir para onde?");
            return false;
        }

        String direcao = comando.getSegundaPalavra();

        // Tenta sair do ambiente atual
        Ambiente proximaSala = salaAtual.getSaida(direcao);
        boolean terminaJogo = false;

        if (proximaSala == null) {
            System.out.println("Não há esse local!");
        } else {

            if (proximaSala instanceof SalaItemPorta) {
                terminaJogo = interagirComSalaItemPorta((SalaItemPorta) proximaSala);
            } else if (proximaSala instanceof SalaInimigo) {
                SalaInimigo sala = (SalaInimigo) proximaSala;
                terminaJogo = interagirComSalaInimigo(sala, sala.getDano());
            } else {
                salaAtual = proximaSala;
            }

            System.out.println(salaAtual.getDescricaoLonga());

        }

        return terminaJogo;

    }

    /**
     * Reponsável pela interação do aventureiro com salas com inimigos.
     * 
     * @param sala A sala com inimigo que o aventureiro entrou.
     * @param dano O dano que o aventureiro recebe do inimigo.
     * @return  true se o aventureiro morre na sala, caso contrário false.
     */
    private boolean interagirComSalaInimigo(SalaInimigo sala, int dano) {
        
        System.out.println("Você é surpreendido por um esqueleto que surge do seu ponto cego.");
        System.out.println("Ele te golpeia com uma faca pequena.");

        aventureiro.recebeDano(dano);
        System.out.println("\nVocê perdeu " + dano + " de vida.\n");

        if(aventureiro.getPontosDeVida() <= 0){
            return true;
        }

        System.out.println("Após sofrer o ataque, você usa " + aventureiro.getArma() + " e retribui o golpe, matando-o.");
        System.out.println("Não há mais nada na sala.");

        salaAtual = sala;
        
        return false;

    }

    /**
     * Responsável pelas interações do aventureiro com salas com itens.
     * 
     * @param sala A sala que o aventureiro entrou.
     * @return true se o aventureiro destrancou a porta, caso contrário false
     */
    private boolean interagirComSalaItemPorta(SalaItemPorta sala) {
        if (!sala.isTrancado()) {
            System.out.println("Você já visitou essa sala...");
            return false;
        }

        String municao = sala.getItem();

        System.out.println("Você está em " + sala.getDescricao() + ".");
        System.out.println("Nela, você encontra " + municao + " e guarda.");

        aventureiro.adicionarItem(municao,
                "Objeto que completa uma arma específica.");
        System.out.println("\nO item foi adicionado ao inventário!\n");

        sala.setTrancado(false);
        salaAtual = sala;

        return true;

    }

}
