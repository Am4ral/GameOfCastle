import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
    private final HashMap<String, String> armasItens;
    private Aventureiro aventureiro;

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
        salaEntrada = new Ambiente("um Salão de Entrada do castelo.");
        // Collections.shuffle(itensFase1);
        List<String> itensFase1 = aleatorizarItens();

        for (String item : itensFase1) {
            System.out.println(item);
        }

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
        salao = new Ambiente("um salão enorme");

        Ambiente sala5, sala6, sala7, sala8;
        sala5 = new Ambiente("uma sala bem organizada");
        sala6 = new SalaDano("uma sala vazia", 25);
        sala7 = new SalaDano("uma sala vazia", 25);
        sala8 = new SalaDano("uma sala vazia", 25);

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
        salao.ajustarSaida("Portao", new Ambiente("um enorme portão de metal"));

        sala5.ajustarSaida("Salao", salao);
        sala6.ajustarSaida("Salao", salao);
        sala7.ajustarSaida("Salao", salao);
        sala8.ajustarSaida("Salao", salao);

        salaAtual = salao;
    }

    private void criarCenarioFase3() {

        SalaNPC corredor = new SalaNPC("um corredor extenso", "Merschmann", "um mago além de seu tempo");
        SalaDano salaTrono = new SalaDano("covil do Dragão", 99);

        corredor.ajustarSaida("Portao", salaTrono);

        salaAtual = corredor;
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

        aventureiro.adicionarItem("Chave simples", "uma chave simples de metal");
        aventureiro.adicionarItem("Chave simples", "uma chave simples de metal");
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
    public String jogarFase1(String comandoUsuario) {
        boolean estaPreparado = possuiItem(armasItens.get(aventureiro.getArma()));
        String mensagemRetorno = "";
        String retornoComando = "";

        if (!estaPreparado && possuiItem("Chave simples")) {
            retornoComando = tratarComando(comandoUsuario);
            if (!retornoComando.equals("sair")) {
                return retornoComando;
            }
        } else {
            mensagemRetorno += "\n\nBatidas violentas em madeira começam a ecoar de fora da sala. Assustado, você retorna à Sala de Entrada para averiguar o barulho."
                    + "\nAlgo está esmurrando de forma violenta as portas que bloqueam o caminho!"
                    + " Ciente da proximidade, você se prepara para o pior."
                    + "\nA porta que antes parecia impossível de quebrar é destruída! Pedaços de madeira voam para os lados."
                    + "\nAo fundo, surge um Orc furioso que avança em sua direção.";
        }

        if (!retornoComando.equals("sair")) {
            if (!estaPreparado) {
                mensagemRetorno += "\nO Orc te ataca brutalmente. Você é incapaz de se defender...";
                aventureiro.recebeDano(aventureiro.getPontosDeVida());
            } else {
                mensagemRetorno += "\nVocê luta bravamente.\nUma batalha intensa finaliza e você sai vitorioso!";
            }
        } else {
            return retornoComando;
        }

        if (aventureiro.existeItem("Chave simples")) {
            aventureiro.removerItem("Chave simples");
        }

        return mensagemRetorno;
    }

    /**
     * Rotina e controle das decisões realizadas.
     */
    public String tratarComando(String comandoUsuario) {
        Comando comando = analisador.getComando(comandoUsuario);
        return processarComando(comando);
    }

    // /**
    // * Verifica se o aventureiro está vivo para avançar de fase.
    // *
    // * @return true se possui mais que 0 de vida, false caso contrário.
    // */
    // private boolean avancaParaFase3() {
    // return (aventureiro.getPontosDeVida() > 0);
    // }

    /**
     * Verifica se o aventureiro possui o item necessário correspondente a sua arma
     * no inventário.
     * 
     * @return true se possui o item, caso contrário false.
     */
    private boolean possuiItem(String item) {
        return aventureiro.existeItem(item);
    }

    /**
     * Imprime a mensagem de derrota.
     */
    public String imprimirDerrota() {
        return "\nSeus olhos fecham..."
                + " Sua vida se esvai."
                + " A escuridão te consome."
                + "\nVocê está morto!";
    }

    public String getComandoUsuario(String mensagem) {
        return mensagem;
    }

    /**
     * Contextualiza o momento final do jogo, indicando sua vitória ou derrota.
     */
    public String finalizar() {
        String mensagemRetorno = "\n\nVocê abre o portão e entrando na sala, você aprecia a imensidão do local."
                + "\nDe repente, duas luzes azuis acendem no teto e você começa a ouvir barulhos de respiração profunda ecoando no local."
                + "\nUm dragão se revela completamente, descendo do teto."
                + " Você perturbou seu descanso... Ele sopra um enorme bafo gelado."
                + "\nVocê se lembra de tudo que passou até chegar nesse momento. Tudo isso não pode ter sido em vão. Então, você se prepara e começa o combate.";

        SalaDano salaTrono = ((SalaDano) salaAtual);

        int dano = salaTrono.getDano();
        aventureiro.recebeDano(dano);

        if (aventureiro.getPontosDeVida() > 0) {
            mensagemRetorno += "\nApós longos minutos de combate, você derrota o dragão. Como troféu, você pega o seu coração e guarda."
                    + aventureiro.adicionarItem("Coração do Dragão", "o coração do maior dragão de gelo já existente");
        } else {
            mensagemRetorno += imprimirDerrota();
        }

        return mensagemRetorno;
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
                + "\nAlém disso, ele te passa uma dica sobre a sala que possui um item para completar sua arma: \"Os Deuses te escolheram! O item que você precisa não está na "
                + imprimirDica() + "\"."
                + "\nÀ sua frente há um enorme portão. À sua esquerda há duas portas e à direita também."
                + "\nCurioso, você se aproxima do portão maior. Não há fechadura. Você tenta abrí-lo, porém, o portão não se move."
                + " Sem sucesso, você retorna.\n" + salaAtual.getDescricaoLonga();
    }

    /**
     * Imprime o contexto da fase 2.
     */
    public String imprimirContextoFase2() {
        criarCenarioFase2();

        return "\n\nAs portas agora estão abertas e, então, você decide avançar."
                + "\nVocê percorre um extenso corredor mal iluminado até alcançar um salão imenso que dá acesso a duas portas à esquerda e a duas portas à direita."
                + "\nAo centro, encontra-se um enorme portão feito de ferro."
                + "\nVocê se aproxima e percebe que ele está trancado, então retorna.\n\n"
                + salaAtual.getDescricaoLonga();
    }

    /**
     * Impressão do contexto da fase 3.
     */
    public String imprimirContextoFase3() {

        criarCenarioFase3();

        return salaAtual.getDescricaoLonga()
                + "\n\nOutro corredor mal iluminado, mas..."
                + "\nAo fundo, um homem com vestes que impossibilitam ver seu rosto encontra-se iluminado por uma única tocha."
                + "\nAinda mais ao fundo, há outra porta de metal, iluminada por duas tochas em cada lateral."
                + "\nCiente de que não há outro caminho, você avança."
                + "\nA cada passo, você se aproxima mais do homem que permanece estático."
                + "\nO homem retira seu manto revelando ser um mago. Quando você passa ao seu lado, ele diz:\n";
    }

    public void finalizarJogador() {
        aventureiro.recebeDano(getVidaAventureiro());
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
        } else if (commandWord.equals("investigar")) {
            return investigar();
        }
        return "";
    }

    /**
     * Responsável pela ação de investigar nas salas do castelo.
     * 
     * @return true caso o comando finalize o jogo, caso contrário false.
     */
    private String investigar() {
        String mensagemRetorno = "";
        // Se estiver na sala com passagem secreta
        if (salaAtual.getDescricao().equals("uma sala bem organizada") && !salaAtual.getVisitado()) {
            mensagemRetorno += "\nA sala apresentava um clima confortável destacado pela organização dos móveis."
                    + "\nObservando um pouco melhor, você percebe um relevo por baixo do tapete."
                    + " Curioso, você decide tirá-lo."
                    + "\nVocê encontrou uma passagem secreta!";
            salaAtual.visitar();
            mensagemRetorno += interagirComPorao(salaAtual);
        } else {
            mensagemRetorno += "\nNão há nada de especial aqui.";
        }
        return mensagemRetorno;
    }

    /**
     * Reponsável pela interação do aventureiro com o porão secreto.
     * 
     * @param saida A sala que da acesso ao porão para ajustar a saída.
     * @return true caso o jogador erre o enigma e morra, caso contrário false.
     */
    private String interagirComPorao(Ambiente saida) {
        SalaNPC porao = new SalaNPC("um porão um pouco escuro", "esfinge", "uma esfinge com feição séria");
        porao.ajustarSaida("SalaConfortavel", saida);
        salaAtual = porao;

        return "\nVocê desce um lance de escadas até chegar ao porão."
                + "\nVocê encontra uma " + porao.getNomeNpc() + ", e ela diz:"
                + "\n\"Aventureiro, você possui uma chance para aceitar meu enigma, caso contrário, morrerá aqui nesse porão."
                + " Preste atenção, não repetirei novamente\".";
    }

    public String gerarEnigma() {
        return ((SalaNPC) salaAtual).getEnigmaAleatorio();
    }

    public String analisarRespostaEnigmaEsfinge(String resposta, String enigma) {
        if (((SalaNPC) salaAtual).acertouEnigma(resposta.toLowerCase(), enigma)) {
            return "\n\nA esfinge some e um baú aparece."
                    + "\nVocê abre, encontra uma chave muito bem detalhada e pega."
                    + aventureiro.adicionarItem("Chave do Grande Portão", "uma chave detalhada, com entalhes em metal.")
                    + "\n\n" + salaAtual.getDescricaoLonga();
        }

        aventureiro.recebeDano(aventureiro.getPontosDeVida());
        return "\n\nUma espada é cravada em suas costas e atravessa seu peito.";
    }

    /**
     * Responsável pela interação com o portão da segunda fase.
     * 
     * @return true caso o aventureiro abra, caso contrário false.
     */
    private String interagirComPortao() {
        if (!possuiItem("Chave do Grande Portão")) {
            return "\n\nHá uma fechadura..."
                    + "\nTalvez exista uma chave capaz de abrí-la.";
        }

        return "\n\nVocê abre a fechadura com a chave que encontrou."
                + "\nO portão se abre..."
                + aventureiro.removerItem("Chave do Grande Portão");
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
            retorno = "\nNão há esse local!";
        } else {
            if (proximaSala instanceof SalaItemPorta) {
                retorno = interagirComSalaItemPorta((SalaItemPorta) proximaSala);
            } else if (proximaSala.getDescricao().equals("uma sala vazia")) {
                SalaDano sala = (SalaDano) proximaSala;
                retorno = interagirComSalaInimigo(sala, sala.getDano());
            } else if (proximaSala.getDescricao().equals("um enorme portão de metal")) {
                retorno = interagirComPortao();
            } else {
                salaAtual = proximaSala;
                if (proximaSala.getDescricao().equals("covil do Dragão")) {
                    retorno = finalizar();
                }
            }

            if (!retorno.equals("\nVocê já visitou essa sala...") && !retorno.contains("morto")
                    && !retorno.contains("portão se abre") && !retorno.contains("bafo gelado")) {
                retorno += "\n\n" + salaAtual.getDescricaoLonga();
            }
        }

        return retorno;
    }

    /**
     * Reponsável pela interação do aventureiro com salas com inimigos.
     * 
     * @param sala A sala com inimigo que o aventureiro entrou.
     * @param dano O dano que o aventureiro recebe do inimigo.
     * @return true se o aventureiro morre na sala, caso contrário false.
     */
    private String interagirComSalaInimigo(SalaDano sala, int dano) {
        String mensagemRetorno = "\n\nVocê é surpreendido por um esqueleto que surge do seu ponto cego."
                + "\nEle te golpeia com uma faca pequena."
                + aventureiro.recebeDano(dano);

        if (aventureiro.getPontosDeVida() <= 0) {
            return mensagemRetorno + "\n" + imprimirDerrota();
        }

        salaAtual = sala;

        return mensagemRetorno
                + "\nApós sofrer o ataque, você usa " + aventureiro.getArma() + " e retribui o golpe, matando-o."
                + "\nNão há mais nada na sala.";
    }

    public int getVidaAventureiro() {
        return aventureiro.getPontosDeVida();
    }

    /**
     * Retorna a arma do aventureiro
     * 
     * @return String contendo o nome da arma selecionada pelo jogador
     */
    public String getArmaAventureiro() {
        return aventureiro.getArma();
    }

    /**
     * Retorna o nome do aventureiro
     * 
     * @return String contendo o nome digitado pelo jogador no começo do jogo.
     */
    public String getNomeAventureiro() {
        return aventureiro.getNome();
    }

    /**
     * Coloca a arma selecionada pelo jogador no inventário dele
     * 
     * @param String com o nome da arma que o jogador selecionou.
     * @return String vazia em caso de sucesso, ou mensagem de erro em falha.
     */
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

    /**
     * Retorna o inventário do jogador.
     * 
     * @return List<Item> contendo os itens que o jogador tem.
     */
    public List<Item> getItensAventureiro() {
        return aventureiro.getItens();
    }

    /**
     * Responsável pelas interações do aventureiro com salas com itens.
     * 
     * @param sala A sala que o aventureiro entrou.
     * 
     * @return String contendo uma mensagem infomando que o jogador já visitou a
     *         sala,
     *         ou um texto descrevendo o que acontece caso ela ainda não tenha sido
     *         visitada.
     */
    private String interagirComSalaItemPorta(SalaItemPorta sala) {
        if (!sala.isTrancado()) {
            return "\nVocê já visitou essa sala...";
        }

        String municao = sala.getItem();
        sala.setTrancado(false);
        salaAtual = sala;

        return aventureiro.removerItem("Chave simples")
                + "\n\nVocê está em " + salaAtual.getDescricao() + ".\n" + "Nela, você encontra um(a) " + municao
                + " e guarda."
                + aventureiro.adicionarItem(municao, "Objeto que completa uma arma específica.");
    }

    /**
     * Analisa se a resposta que o jogador digitou está correta
     * 
     * @param String contendo a resposta
     * @param String contendo o enigma
     * 
     * @return em caso de sucesso retorna uma mensagem de parabéns,
     *         em caso de falha retorna um mensagem de consolo.
     */
    public String analisarRespostaEnigmaDoMago(String resposta, String enigma) {
        String mensagemRetorno = "";

        if (((SalaNPC) salaAtual).acertouEnigma(resposta.toLowerCase(), enigma) && !enigma.equals("")) {
            mensagemRetorno += "\n\"Parabéns colega! Tome esse presente.\". Você recebe uma poção de cura e toma."
                    + aventureiro.curarVida();
        } else {
            mensagemRetorno += "\n\"Está tudo bem, as vezes nós erramos...\"";
        }

        return mensagemRetorno += "\nVocê agradece e se vira para partir."
                + " Quando vira novamente para trás, o mago não está mais lá."
                + "\nVocê segue seu caminho até o portão. \n\n"
                + salaAtual.getDescricaoLonga();
    }
}
