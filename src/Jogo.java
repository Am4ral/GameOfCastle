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
    private Aventureiro aventureiro;
    private String[] armas = { "Espada", "Estilingue", "Revólver", "Arco" };
    private String mensagemUsuario;

    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo() {
        analisador = new Analisador();

        criarAmbiente();
    }

    /**
     * Cria todos os ambientes e liga as saidas deles
     */
    private void criarAmbiente() {
    
    }

    public void personalizarAventureiro() {
        aventureiro = new Aventureiro(escolheArma());
    }

    public String exibirMensagemArma() {
        String mensagemArmas = "";

        for (int i = 0; i < armas.length; i++) {
            mensagemArmas += (i + 1) + " - " + armas[i] + "\n";
        }

        mensagemArmas += "Escolha o número da sua arma:";

        return mensagemArmas;
    }

    private String escolheArma() {
        int posicao = Integer.parseInt(mensagemUsuario);
        posicao -= 1;

        if (posicao < 0 || posicao > armas.length - 1) {
            throw new IndexOutOfBoundsException(
                    "Essa arma não existe. Por favor, digite um número válido.\nEscolha o número da sua arma:");
        }
        return armas[posicao];
    }

    /**
     * Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public void jogar() {
        imprimirBoasVindas();

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
    public String imprimirBoasVindas() {
        return "Bem vindo ao Game of Castle!\nGame of Castle é um jogo aventura incrível.\nDigite 'help' se precisar de ajuda.";
    }

    public void imprimirContextoInicial() {
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
            System.out.println("I don't know what you mean...");
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
     * Aqui nos imprimimos algo bobo e enigmatico e a lista de
     * palavras de comando
     */
    private void imprimirAjuda() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your comando words are:");
        analisador.mostrarComandos();
    }

    /**
     * Tenta ir em uma direcao. Se existe uma saida entra no
     * novo ambiente, caso contrario imprime mensagem de erro.
     */
    private void irParaAmbiente(Comando comando) {
        if (!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            System.out.println("Go where?");
            return;
        }

        String direction = comando.getSegundaPalavra();

        // Tenta sair do ambiente atual
        Ambiente proximaSala = salaAtual.getSaida(direction);

        if (proximaSala == null) {
            System.out.println("There is no door!");
        } else {
            salaAtual = proximaSala;

            /*
             * if (salaAtual instanceof SalaItemPorta){
             * String municao = salaAtual.getMunicao();
             * System.out.println("Você encontrou " + municao + "!");
             * 
             * aventureiro.adicionarItem(municao,
             * "Objeto que completa uma arma específica.");
             * System.out.println("O item foi adicionado ao inventário.");
             * }
             */

            System.out.println(salaAtual.getDescricaoLonga());
        }
    }

    public int getVidaAventureiro() {
        return aventureiro.getPontosDeVida();
    }

    public String getArmaAventureiro() {
        return aventureiro.getArma();
    }

    public void getRespostaInterface(String mensagem) {
        mensagemUsuario = mensagem;
        personalizarAventureiro();
    }

    /**
     * "Sair" foi digitado. Verifica o resto do comando pra ver
     * se nos queremos realmente sair do jogo.
     * 
     * @return true, se este comando sai do jogo, false, caso contrario
     */
    private boolean sair(Comando comando) {
        if (comando.temSegundaPalavra()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true; // sinaliza que nos queremos sair
        }
    }
}
