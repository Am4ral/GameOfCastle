import ambientes.Ambiente;
import ambientes.SalaItemPorta;

import java.util.ArrayList;
import java.util.Collections;
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
    private final List<String> itensFase1 = new ArrayList<>();
    
    private Aventureiro aventureiro;
    private Scanner entrada;

    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo() {
        
        entrada = new Scanner(System.in);
        analisador = new Analisador();
        
        itensFase1.add("Aljava com flechas");
        itensFase1.add("Saquinho com pedras");
        itensFase1.add("Cartucho de munições");
        itensFase1.add("Pedra de amolar");
        
        criarAmbiente();
    }

    /**
     * Cria todos os ambientes e liga as saidas deles
     */
    private void criarAmbiente() {
        //Ambientes Fase 1
        Ambiente salaEntrada;
        SalaItemPorta sala1, sala2, sala3, sala4;

        // cria os ambientes
        salaEntrada = new Ambiente("Você está no salão de entada do Castelo! \nVocê consegue enxergar à sua frente um grande portão, na sua esquerda estão duas portas que aparentam levar para salas. À sua direita existem mais duas portas que também parecem levar a salas. \nVocê se aproxima do portão e tentar abrir ele, porem ele não se move e também não tem nenhuma fechadura para colocar as chaves que o mago te deu.");
        Collections.shuffle(itensFase1);
        sala1 = new SalaItemPorta("em uma sala com um ", itensFase1.get(0));
        sala2 = new SalaItemPorta("em uma sala com um ", itensFase1.get(1));
        sala3 = new SalaItemPorta("em uma sala com um ", itensFase1.get(2));
        sala4 = new SalaItemPorta("em uma sala com um ", itensFase1.get(3));

        // inicializa as saidas dos ambientes
        salaEntrada.ajustarSaida("Sala1", sala1);
        salaEntrada.ajustarSaida("Sala2", sala2);
        salaEntrada.ajustarSaida("Sala3", sala3);
        salaEntrada.ajustarSaida("Sala4", sala4);

        sala1.ajustarSaida("Sul", salaEntrada);
        sala2.ajustarSaida("Sul", salaEntrada);
        sala3.ajustarSaida("Sul", salaEntrada);
        sala4.ajustarSaida("Sul", salaEntrada);

        //Ambientes Fase 2

        //Ambientes Fase 3

        salaAtual = salaEntrada; // ambiente em que é iniciado o jogo
    }

    private void personalizarAventureiro() {
        System.out.print("Digite o nome do seu aventureiro: ");
        String nome = entrada.nextLine();
        String arma = escolheArmas();

        aventureiro = new Aventureiro(nome, arma);
    }

    private String escolheArmas() {

        String[] armas = { "Espada", "Estilingue", "Revólver", "Arco" };

        for (int i = 0; i < armas.length; i++) {
            System.out.println((i + 1) + " - " + armas[i]);
        }

        System.out.print("Escolha o número da sua arma: ");

        int posicao = entrada.nextInt();
        posicao -= 1;

        while (true) {
            if (posicao < 0 || posicao > armas.length) {
                System.out.println("Essa arma não existe. Por favor, digite um número válido.");
                System.out.print("Escolha o número da sua arma: ");
                posicao = entrada.nextInt();
            } else {
                return armas[posicao];
            }
        }

    }

    /**
     * Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogar() {
        
        imprimirBoasVindas();
        personalizarAventureiro();
        imprimirContextoInicial();

        // Entra no loop de comando principal. Aqui nos repetidamente lemos
        // comandos e os executamos ate o jogo terminar.

        boolean terminado = false;
        while (!terminado) {
            Comando comando = analisador.getComando();
            terminado = processarComando(comando);
        }
        System.out.println("Obrigado por jogar. Ate mais!");
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private void imprimirBoasVindas() {
        System.out.println("\nBem vindo ao Game of Castle!");
        System.out.println("Game of Castle é um jogo aventura incrível.");
        System.out.println("Digite 'help' se precisar de ajuda.\n");
    }

    private void imprimirContextoInicial() {
        System.out.println("\nVocê é um aventureiro e está prestes a entrar em um grande castelo.");
        System.out.println("Na entrada, você se encontra com um ancião e recebe duas chaves. Além disso," +
                "ele te passa uma dica sobre a sala que possui um item para completar sua arma:");
        System.out.println(/* dica da sala */);
        System.out.println(salaAtual.getDescricaoLonga());
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * 
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo, caso contrário false.
     */
    private boolean processarComando(Comando comando) {
        boolean querSair = false;

        if (comando.ehDesconhecido()) {
            System.out.println("Não sei o que você quer dizer...");
            return false;
        }

        String commandWord = comando.getPalavraDeComando();
        if (commandWord.equals("ajuda")) {
            imprimirAjuda();
        } else if (commandWord.equals("ir")) {
            irParaAmbiente(comando);
        } else if (commandWord.equals("sair")) {
            querSair = sair(comando);
        }

        return querSair;
    }

    // Implementacoes dos comandos do usuario

    /**
     * Printe informacoes de ajuda.
     */
    private void imprimirAjuda() {
        System.out.println("Seus comandos são:");
        analisador.mostrarComandos();
    }

    private void irParaAmbiente(Comando comando) {
        if (!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            System.out.println("Ir para onde?");
            return;
        }

        String direcao = comando.getSegundaPalavra();

        // Tenta sair do ambiente atual
        Ambiente proximaSala = salaAtual.getSaida(direcao);

        if (proximaSala == null) {
            System.out.println("Não há esse local!");
        } else {
            salaAtual = proximaSala;
            
            /*if (salaAtual instanceof SalaItemPorta){
                String municao = salaAtual.getMunicao();
                System.out.println("Você encontrou " + municao + "!");
                
                aventureiro.adicionarItem(municao, "Objeto que completa uma arma específica.");
                System.out.println("O item foi adicionado ao inventário.");
            }*/
            
            System.out.println(salaAtual.getDescricaoLonga());
        }
    }

    /**
     * "Sair" foi digitado. Verifica o resto do comando pra ver
     * se nos queremos realmente sair do jogo.
     * 
     * @return true, se este comando sai do jogo, false, caso contrario
     */
    private boolean sair(Comando comando) {
        if (comando.temSegundaPalavra()) {
            System.out.println("Sair?");
            return false;
        } else {
            return true; // sinaliza que nos queremos sair
        }
    }
}
