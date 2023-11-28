import ambientes.Ambiente;
import ambientes.SalaItemPorta;

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
    private final List<String> itensFase1 = new ArrayList<>();

    private Aventureiro aventureiro;
    private String[] armas = { "Espada", "Estilingue", "Revólver", "Arco" };

    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo() {
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
        // Ambientes Fase 1
        Ambiente salaEntrada;
        SalaItemPorta sala1, sala2, sala3, sala4;

        // cria os ambientes
        salaEntrada = new Ambiente("no salão de entrada do castelo.");
        Collections.shuffle(itensFase1);
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

        // Ambientes Fase 2

        // Ambientes Fase 3

        salaAtual = salaEntrada; // ambiente em que é iniciado o jogo
    }

    public void personalizarAventureiro(String nome) {
        aventureiro = new Aventureiro(nome);
    }

    public String exibirMensagemArma() {
        String mensagemArmas = "Escolha o número da sua arma:\n";

        for (int i = 0; i < armas.length; i++) {
            mensagemArmas += (i + 1) + " - " + armas[i] + "\n";
        }

        return mensagemArmas;
    }

    private String escolheArma(String arma) {
        int posicao = Integer.parseInt(arma);
        posicao -= 1;

        if (posicao < 0 || posicao > armas.length - 1) {
            throw new IndexOutOfBoundsException(
                    "Essa arma não existe. Por favor, digite um número válido.\nEscolha o número da sua arma:\n");
        }
        return armas[posicao];
    }

    /**
     * Rotina principal do jogo. Fica em loop ate terminar o jogo.
     */
    public String jogar(String comandoUsuario) {

        Comando comando = analisador.getComando(comandoUsuario);
        return processarComando(comando);

    }

    public String mensagemFimJogo() {
        return "Obrigado por jogar. Ate mais!";
    }

    public String getComandoUsuario(String mensagem) {
        return mensagem;
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    public String imprimirBoasVindas() {
        return "Bem vindo ao Game of Castle!\nGame of Castle é um jogo aventura incrível.\nDigite 'help' se precisar de ajuda.\n\n";
    }

    public String imprimirContextoInicial() {

        return "\nVocê é um aventureiro e está prestes a entrar em um grande castelo.\n"
                + "Na entrada, você se encontra com um ancião e recebe duas chaves.\n"
                + "Além disso, ele te passa uma dica sobre a sala que possui um item para completar sua arma:"
                // inserir dica
                + salaAtual.getDescricao()
                + "\nA sua frente há um enorme portão. A sua esquerda há duas portas e a direita também.\n"
                + "Curioso, você se aproxima do portão maior. Não há fechadura. Você tenta abrí-lo, porém, o portão não se move.\n"
                + "Sem sucesso, você retorna.\n" + salaAtual.getDescricaoLonga();
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * 
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo, caso contrário false.
     */
    private String processarComando(Comando comando) {
        if (comando.ehDesconhecido()) {
            return "Não sei o que você quer dizer...";
        }

        String commandWord = comando.getPalavraDeComando();

        if (commandWord.equals("ajuda")) {
            return imprimirAjuda();
        } else if (commandWord.equals("ir")) {
            return irParaAmbiente(comando);
        } else if (commandWord.equals("sair")) {
            // querSair = sair(comando);
        }
        return "";
    }

    // Implementacoes dos comandos do usuario

    /**
     * Printe informacoes de ajuda.
     */
    private String imprimirAjuda() {
        return "Seus comandos são:\n" + analisador.mostrarComandos() + "\n";
    }

    private String irParaAmbiente(Comando comando) {
        if (!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            return "Ir para onde?";
        }

        String direcao = comando.getSegundaPalavra();

        // Tenta sair do ambiente atual
        Ambiente proximaSala = salaAtual.getSaida(direcao);

        if (proximaSala == null) {
            return "Não há esse local!\n";
        } else {
            String retorno = "";
            salaAtual = proximaSala;

            if (salaAtual instanceof SalaItemPorta) {

                String municao = ((SalaItemPorta) salaAtual).getItem();

                aventureiro.adicionarItem(municao,
                        "Objeto que completa uma arma específica.");

                retorno += "\nVocê está em " + salaAtual.getDescricao() + ".\n" + "Nela, você encontra " + municao
                        + " e guarda.\n" + "O item foi adicionado ao inventário!\n";
            }
            retorno += salaAtual.getDescricaoLonga() + "\n";

            return retorno + "\n";
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
        }
        return "";
    }

    public List<Item> getItensAventureiro() {
        return aventureiro.getItens();
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
