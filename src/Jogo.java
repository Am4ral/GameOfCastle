import ambientes.Ambiente;
import ambientes.SalaDano;
import ambientes.SalaItemPorta;
import ambientes.SalaNPC;
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
        salao.ajustarSaida("Porta", new Ambiente("um enorme portão de metal"));

        sala5.ajustarSaida("Salao", salao);
        sala6.ajustarSaida("Salao", salao);
        sala7.ajustarSaida("Salao", salao);
        sala8.ajustarSaida("Salao", salao);

        salaAtual = salao;
    }

    private String criarCenarioFase3() {
        
        SalaNPC corredor = new SalaNPC("um corredor extenso", "Merschmann", "um mago além de seu tempo");
        SalaDano salaTrono = new SalaDano("covil do Dragão", 99);

        corredor.ajustarSaida("Porta", salaTrono);
        
        salaAtual = corredor;
        return salaAtual.getDescricaoLonga();
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
    public String jogarFase1(String comandoUsuario) {
        boolean estaPreparado = estaPreparado(armasItens.get(aventureiro.getArma()));
        String mensagemRetorno = "";
        String retornoComando = "";

        if (!estaPreparado && temChave()) {
            Comando comando = analisador.getComando(comandoUsuario);
            retornoComando = processarComando(comando);
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

        if(!retornoComando.equals("sair")){
            if (!estaPreparado) {
                mensagemRetorno += "\nO Orc te ataca brutalmente. Você é incapaz de se defender...";
                aventureiro.recebeDano(100);
            } else {
                mensagemRetorno += "\nVocê luta bravamente.\nUma batalha intensa finaliza e você sai vitorioso!";
            }
        }else{
            return retornoComando;
        }
        
        if(aventureiro.existeItem("Chave simples")){
            aventureiro.removerItem("Chave simples");
        }

        return mensagemRetorno;
    }

    public String jogarFase2(String comandoUsuario){
        Comando comando = analisador.getComando(comandoUsuario);
        return processarComando(comando);
    }

    /**
     * Rotina e controle das decisões realizadas na fase 3.
     */
    private String jogarFase3(String comandoUsuario) {

        Comando comando = analisador.getComando(comandoUsuario);
        return processarComando(comando);
    }

    /**
     * Verifica se o aventureiro está vivo para avançar de fase.
     * 
     * @return true se possui mais que 0 de vida, false caso contrário.
     */
    private boolean avancaParaFase3() {
        return (aventureiro.getPontosDeVida() > 0);
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
    private void finalizar() {
        System.out.println("Você abre os portões que abrem para uma sala imensa.");
        System.out.println("Entrando na sala, você aprecia a imensidão do local.");
        System.out.println(
                "Olhando para o teto, você percebe que somente o teto não esta iluminado o que da uma impressão de profundidade.");
        System.out.println("De repente, duas luzes azuis acendem no teto.");
        System.out.println("Você começa a ouvir barulhos de respiração profunda ecoando no local.");
        System.out.println("Um dragão se revela completamente descendo do teto.");
        System.out.println("Você perturbou seu descanso...");

        System.out.println("\nEle sopra um enorme bafo gelado.");

        SalaDano salaTrono = ((SalaDano) salaAtual);

        int dano = salaTrono.getDano();
        aventureiro.recebeDano(dano);

        System.out.println("Você se lembra de tudo que passou até chegar nesse momento.");
        System.out.println("Tudo isso não pode ter sido em vão.");
        System.out.println("Então, você se prepara e começa o combate.");

        if (aventureiro.getPontosDeVida() > 0) {
            System.out.println("\nApós longos minutos de combate, você derrota o dragão.");
            System.out.println("Como troféu, você pega o coração do dragão e guarda.");

            aventureiro.adicionarItem("Coração do Dragão", "o coração do maior dragão de gelo já existente");

            imprimirVitoria();
        } else {
            imprimirDerrota();
        }
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
    private String imprimirContextoFase2() {
        return "As portas agora estão abertas e, então, você decide avançar."
        + "\nVocê percorre um extenso corredor mal iluminado até alcançar um salão imenso\nque dá acesso a duas portas à esquerda e a duas portas à direita."
        + "\nAo centro, encontra-se um enorme portão feito de ferro."
        + "\nVocê se aproxima e percebe que o mesmo encontra-se trancado e retorna.\n"
        + salaAtual.getDescricaoLonga();
    }

    /**
     * Impressão do contexto da fase 3.
     */
    private void imprimirContextoFase3() {
        System.out.println("\nOutro corredor mal iluminado, mas...");
        System.out.println(
                "Ao fundo, um homem com vestes que impossibilitam ver seu rosto\nencontra-se iluminado por uma única tocha.");
        System.out.println("Ainda mais ao fundo, há outra porta de metal iluminada por duas tochas em cada lateral.");
        System.out.println("Ciente de que não há outro caminho, você avança.\n");
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
    private boolean investigar() {

        boolean morreu = false;
        // Se estiver na sala com passagem secreta
        if (salaAtual.getDescricao().equals("uma sala bem organizada") && !salaAtual.getVisitado()) {
            System.out.println("\nA sala apresentava um clima confortável destacado pela organização dos móveis.");
            System.out.println("Observando um pouco melhor, você percebe um relevo por baixo do tapete.");
            System.out.println("Curioso, você decide tirá-lo.");
            System.out.println("\nVocê encontrou uma passagem secreta!\n");
            salaAtual.visitar();
            if (interagirComPorao(salaAtual)) {
                morreu = true;
            }
        } else {
            System.out.println("Não há nada de especial aqui.");
        }

        return morreu;
    }

    /**
     * Reponsável pela interação do aventureiro com o porão secreto.
     * 
     * @param saida A sala que da acesso ao porão para ajustar a saída.
     * @return true caso o jogador erre o enigma e morra, caso contrário false.
     */
    private boolean interagirComPorao(Ambiente saida) {

        SalaNPC porao = new SalaNPC("um porão um pouco escuro", "esfinge", "uma esfinge com feição séria");

        porao.ajustarSaida("SalaConfortavel", saida);

        salaAtual = porao;

        System.out.println("Você desce um lance de escadas até chegar ao porão.");
        System.out.println("Você encontra " + porao.getNomeNpc() + ", e ela diz:\n");
        System.out.println(
                "\"Aventureiro, você possui uma chance para aceitar meu enigma, caso contrário, morrerá aqui nesse porão.\"");
        System.out.println("\"Preste atenção, não repetirei novamente\"\n");

        String enigma = porao.getEnigmaAleatorio();
        System.out.println(enigma);

        entrada.nextLine();
        String resposta = entrada.nextLine();

        if (porao.acertouEnigma(resposta, enigma)) {
            System.out.println("\n\"Parabéns, você acertou.\"");
            System.out.println("A esfinge some e um baú aparece.");
            System.out.println("Você abre, encontra um chave muito bem detalhada e pega.\n");

            aventureiro.adicionarItem("Chave do Grande Portao", "uma chave detalhada com entalhes em metal.");
            System.out.println(salaAtual.getDescricaoLonga());

            return false;
        }

        System.out.println("Uma espada é cravada em suas costas e atravessa seu peito.");
        aventureiro.recebeDano(aventureiro.getPontosDeVida());

        return true;
    }

    /**
     * Responsável pela interação com o portão da segunda fase.
     * 
     * @return true caso o aventureiro abra, caso contrário false.
     */
    private boolean interagirComPortao() {
        if (!estaPreparado("Chave do Grande Portao")) {
            System.out.println("\nHá uma fechadura...");
            System.out.println("Talvez exista uma chave capaz de abrí-la.\n");
            return false;
        }

        System.out.println("Você abre a fechadura com a chave que encontrou.");
        System.out.println("O portão se abre...");
        return true;
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
            } else if (proximaSala.getDescricao().equals("uma sala vazia")){
                SalaDano sala = (SalaDano) proximaSala;
                return interagiriComSalaInimigo(sala, sala.getDano());
            } else if (proximaSala.getDescricao() == "um enorme portão de metal") {
                return interagirComPortao();
            } else {
                salaAtual = proximaSala;
                if (proximaSala.getDescricao().equals("covil do Dragão")) {
                    return true;
                }
            }

            if (!retorno.equals("\nVocê já visitou essa sala...")) {
                retorno += "\n\n" + salaAtual.getDescricaoLonga();
            }

            return retorno;
        }
    }

    /**
     * Reponsável pela interação do aventureiro com salas com inimigos.
     * 
     * @param sala A sala com inimigo que o aventureiro entrou.
     * @param dano O dano que o aventureiro recebe do inimigo.
     * @return true se o aventureiro morre na sala, caso contrário false.
     */
    private boolean interagirComSalaInimigo(SalaDano sala, int dano) {

        System.out.println("Você é surpreendido por um esqueleto que surge do seu ponto cego.");
        System.out.println("Ele te golpeia com uma faca pequena.");

        aventureiro.recebeDano(dano);

        if (aventureiro.getPontosDeVida() <= 0) {
            return true;
        }

        System.out.println("Após sofrer o ataque, você usa " + aventureiro.getArma() + " e retribui o golpe, matando-o.");
        System.out.println("Não há mais nada na sala.\n");

        salaAtual = sala;

        return false;

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
     * Responsável pelo contexto de interação com o Mago presente na terceira fase.
     */
    // private void interagirComMago() {
    //     System.out.println("A cada passo, você se aproxima mais do homem que permanece estático.");
    //     System.out.println("Quando você passa ao seu lado, ele diz:\n");
    //     System.out.println("\"Gostaria de jogar um jogo comigo?\"");

    //     System.out.println("Escolha (Sim/Nao): ");
    //     String resposta = entrada.nextLine();

    //     while (!resposta.equals("Sim") && !resposta.equals("Nao")) {
    //         System.out.println("Resposta inválida. Tente novamente.");
    //         resposta = entrada.nextLine();
    //     }

    //     if (resposta.equals("Sim")) {
    //         responderEnigmaDoMago();
    //     }

    //     System.out.println("Você segue seu caminho até a porta.\n");
    //     System.out.println(salaAtual.getDescricaoLonga());
    // }

    /**
     * Criação do contexto de resposta do enigma do mago.
     */
    // private void responderEnigmaDoMago() {

    //     String enigma = ((SalaNPC) salaAtual).getEnigmaAleatorio();

    //     System.out.println("\nO homem retira seu manto revelando ser uma mago.");
    //     System.out.println("\"Que bom aventureiro! Eis a sua pergunta.\"");
    //     System.out.println(enigma);

    //     String resposta = entrada.nextLine();

    //     if (((SalaNPC) salaAtual).acertouEnigma(resposta, enigma)) {
    //         System.out.println("\n\"Parabéns colega! Tome esse presente.\"");
    //         System.out.println("Você recebe uma poção de cura e toma.");

    //         aventureiro.curarVida();
            
    //     } else {
    //         System.out.println("\"Está tudo bem, as vezes nós erramos.");
    //     }

    //     System.out.println("Você agradece e se vira para partir");
    //     System.out.println("Quando virá novamente para trás, o mago não está mais lá.");

    // }

}
