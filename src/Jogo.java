import javax.sound.sampled.Port;
import java.util.ArrayList;

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
 * ambientes, cria o analisador e comeca o jogo. Ela tambeme avalia e
 * executa os comandos que o analisador retorna.
 * 
 * @author Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */

public class Jogo {
    private Analisador analisador;
    private Ambiente ambienteAtual;

    /**
     * Cria o jogo e incializa seu mapa interno.
     */
    public Jogo() {
        criarAmbientes();
        analisador = new Analisador();
    }

    /**
     * Cria todos os ambientes e liga as saidas deles
     */
    private void criarAmbientes() {

        //FASE 1
        Ambiente fora, salaEntrada, sala1, sala2, sala3, sala4;
        Portao portao1;
        ArrayList<Ambiente> salasFase1 = new ArrayList<>();

        //FASE 2
        Ambiente corredor, salao, sala5, sala6, sala7, sala8, porao;
        Portao portao2;
        ArrayList<Ambiente> salasFase2 =  new ArrayList<>();

        //FASE 3
        Ambiente corredor2, salaTrono;


        //OS AMBIENTES QUE INTERLIGAM AS FASES PRECISAM SER INICIALIZADOS ANTES
        corredor = new Ambiente("um longo corredor frio");
        corredor2 = new Ambiente("um longo corredor frio, uma figura encapuzada parece te aguardar no final dele");



        // FASE 1
        // cria os ambientes
        fora = new Ambiente("do lado de fora da entrada principal do castelo");
        salaEntrada = new Ambiente("na sala de entrada do castelo");
        sala1 = new Ambiente("na sala 1");
        sala2 = new Ambiente("na sala 2");
        sala3 = new Ambiente("na sala 3");
        sala4 = new Ambiente("na sala 4");
        portao1 = new Portao("em um grande portão trancado");
        salasFase1.add(sala1);
        salasFase1.add(sala2);
        salasFase1.add(sala3);
        salasFase1.add(sala4);

        // inicializa as saidas dos ambientes da primeira fase
        fora.ajustarSaidas( salaEntrada, null, null, null,null);
        salaEntrada.ajustarSaidas(null, null, portao1, fora,salasFase1);
        sala1.ajustarSaidas(null, salaEntrada, null, null,null);
        sala2.ajustarSaidas(null, null, salaEntrada, null,null);
        sala4.ajustarSaidas(null, null, salaEntrada, null,null);
        sala3.ajustarSaidas(null, null, salaEntrada, null,null);
        portao1.ajustarSaidas(corredor, null, salaEntrada, null, null);


        // FASE 2
        //Cria os ambientes
        salao = new Ambiente("um grande salao quadrado com 4 salas nas laterais e um grande portão em frente");
        sala5 = new Ambiente("na sala 5");
        sala6 = new Ambiente("na sala 6");
        sala7 = new Ambiente("na sala 7");
        sala8 = new Ambiente("na sala 8");
        porao = new Ambiente("Um porao escuro com uma esfinge");
        portao2 = new Portao("em um grande portão trancado");
        salasFase2.add(sala5);
        salasFase2.add(sala6);
        salasFase2.add(sala7);
        salasFase2.add(sala8);

        //inicializa as saindas dos ambientes da segunda fase
        corredor.ajustarSaidas(salao,null, salaEntrada, null, null);
        salao.ajustarSaidas(portao2, null, corredor, null, salasFase2);
        sala5.ajustarSaidas(null, salaEntrada, null, null, null);
        sala6.ajustarSaidas(null, salaEntrada, null, null, null);
        sala7.ajustarSaidas(null, null, null, salaEntrada, null);
        sala8.ajustarSaidas(null, null, null, salaEntrada, null);
        porao.ajustarSaidas(null, null, sala5, null, null);
        portao2.ajustarSaidas(corredor2, null, salao, null,null);




        // FASE 3
        //Cria os ambientes
        salaTrono = new Ambiente("uma grande sala com um trono, em cima dele descansa um dragão branco de olhos azuis");

        //inicializa as saindas dos ambientes da terceira fase
        corredor2.ajustarSaidas(salaTrono,null, salao, null, null);
        salaTrono.ajustarSaidas(null, null, null, null, null);

        ambienteAtual = fora; // o jogo comeca do lado de fora
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
            Comando comando = analisador.pegarComando();
            terminado = processarComando(comando);
        }
        System.out.println("Obrigado por jogar. Ate mais!");
    }

    /**
     * Imprime a mensagem de abertura para o jogador.
     */
    private void imprimirBoasVindas() {
        System.out.println();
        System.out.println("Bem-vindo ao Game Of Castle!");
        System.out.println("Em um mundo repleto de maravilhas e perigos, nossa história se desdobra diante de você, caro" +
                " aventureiro. Um herói corajoso, munido de um propósito nobre, embarca em uma jornada épica para salvar " +
                "a destemida Princesa Zelda. No coração deste conto mágico, encontra-se um castelo misterioso e gelado, " +
                "erguido pelos caprichos da magia antiga, onde repousa o objeto de seu desejo: o Coração de Gelo.\n" +
                "\n" +
                "A Princesa Zelda, cativa de um feitiço sombrio, vê sua vida em risco e somente o Coração de Gelo detém " +
                "o poder de quebrar essa magia nefasta. Você, o herói valente e destemido, é a última esperança deste " +
                "reino encantado. Com sua espada em punho e a coragem em seu coração, você penetra nas profundezas " +
                "congeladas do Castelo do Dragão de Gelo.\n" +
                "\n" +
                "Neste castelo, um dragão mítico e temido guarda o precioso Coração de Gelo, uma relíquia antiga que " +
                "detém o poder de congelar o mais ardente dos infernos ou derreter o mais gélido dos corações. Suas " +
                "escolhas moldarão o destino de Zelda e do reino, e você enfrentará perigos inimagináveis, enigmas " +
                "mágicos e criaturas misteriosas em sua busca desesperada.\n" +
                "\n" +
                "O destino da Princesa Zelda repousa em seus ombros, e o Castelo do Dragão de Gelo é o local onde o " +
                "destino será selado. Embarque nesta aventura, herói, e prove que o amor e a coragem podem vencer as " +
                "mais sombrias adversidades. A história aguarda, e o futuro de Zelda e do reino depende de você. É hora " +
                "de se tornar a lenda que o mundo precisa.");

        //@TODO Implementar a tela que vai mostrar as opções de armas
        // player.selecionarArma();
        System.out.println("Como todo lendário Herói, você possui uma arma! \nQual sua arma Herói ?:");

        System.out.println("Digite 'ajuda' se voce precisar de ajuda.");
        System.out.println();

        System.out.println("Voce esta " + ambienteAtual.getDescricao());

        System.out.print("Saidas: ");
        if (ambienteAtual.saidaNorte != null) {
            System.out.print("norte ");
        }
        if (ambienteAtual.saidaLeste != null) {
            System.out.print("leste ");
        }
        if (ambienteAtual.saidaSul != null) {
            System.out.print("sul ");
        }
        if (ambienteAtual.saidaOeste != null) {
            System.out.print("oeste ");
        }
        if(ambienteAtual.salas != null){
            for(int i=1; i <= ambienteAtual.salas.size(); i++){
                System.out.print("sala"+i+" ");
            }
        }
        System.out.println();
    }

    /**
     * Dado um comando, processa-o (ou seja, executa-o)
     * 
     * @param comando O Comando a ser processado.
     * @return true se o comando finaliza o jogo.
     */
    private boolean processarComando(Comando comando) {
        boolean querSair = false;

        if (comando.ehDesconhecido()) {
            System.out.println("Eu nao entendi o que voce disse...");
            return false;
        }

        String palavraDeComando = comando.getPalavraDeComando();
        if (palavraDeComando.equals("ajuda")) {
            imprimirAjuda();
        } else if (palavraDeComando.equals("ir")) {
            irParaAmbiente(comando);
        } else if (palavraDeComando.equals("sair")) {
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
        System.out.println("Voce esta dentro do castelo. Voce esta sozinho. Voce caminha");
        System.out.println("pelo castelo.");
        System.out.println();
        System.out.println("Suas palavras de comando sao:");
        System.out.println("   <ir>  <sair>  <ajuda>   ");
    }

    /**
     * Tenta ir em uma direcao. Se existe uma saida entra no
     * novo ambiente, caso contrario imprime mensagem de erro.
     */
    private void irParaAmbiente(Comando comando) {
        if (!comando.temSegundaPalavra()) {
            // se nao ha segunda palavra, nao sabemos pra onde ir...
            System.out.println("Ir pra onde?");
            return;
        }

        String direcao = comando.getSegundaPalavra();

        // Tenta sair do ambiente atual
        Ambiente proximoAmbiente = null;
        if (direcao.equals("norte")) {
            if(ambienteAtual instanceof Portao){
                if(ambienteAtual.estaTrancado()){
                    System.out.print("O portão está trancado! ");
                }
                else{
                    System.out.println("O protão está aberto! Você passa por ele.");
                }
            }
            else{
                proximoAmbiente = ambienteAtual.saidaNorte;
            }
        }
        if (direcao.equals("leste")) {
            proximoAmbiente = ambienteAtual.saidaLeste;
        }
        if (direcao.equals("sul")) {
            proximoAmbiente = ambienteAtual.saidaSul;
        }
        if (direcao.equals("oeste")) {
            proximoAmbiente = ambienteAtual.saidaOeste;
        }

        direcao = direcao.replaceAll("\\D+",""); //Extrai o numero da string de input
        if (direcao.matches("[1-4]")){
            proximoAmbiente = ambienteAtual.salas.get(Integer.parseInt(direcao)-1);
        }

        if (proximoAmbiente == null) {
            System.out.println("Nao ha passagem!");
        } else {
            ambienteAtual = proximoAmbiente;

            System.out.println("Voce esta " + ambienteAtual.getDescricao());

            System.out.print("Saidas: ");
            if (ambienteAtual.saidaNorte != null) {
                System.out.print("norte ");
            }
            if (ambienteAtual.saidaLeste != null) {
                System.out.print("leste ");
            }
            if (ambienteAtual.saidaSul != null) {
                System.out.print("sul ");
            }
            if (ambienteAtual.saidaOeste != null) {
                System.out.print("oeste ");
            }
            if(ambienteAtual.salas != null){
                for(int i=1; i <= ambienteAtual.salas.size(); i++){
                    System.out.print("sala"+i+" ");
                }
            }
            System.out.println();
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
            System.out.println("Sair o que?");
            return false;
        } else {
            return true; // sinaliza que nos queremos sair
        }
    }
}
