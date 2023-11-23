import java.util.Scanner;

/**
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.
 * 
 * Esse analisador le a entrada do usuario e tenta interpreta-la como um
 * comando "Adventure". Cada vez que eh chamado ele le uma linha do terminal
 * e tenta interpretar a linha como um comando de duas palavras. Ele retorna
 * o comando como um objeto da classe Comando.
 *
 * O analisador tem um conjunto de palavras de comando conhecidas. Ele compara
 * a entrada do usuario com os comandos conhecidos, e se a entrada nao eh um
 * dos comandos conhecidos, ele retorna um objeto comando que eh marcado como
 * um comando desconhecido.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Analisador {
    private PalavrasComando comandos; // guarda todas as palavras de comando validas
    private Scanner entrada; // origem da entrada de comandos

    /**
     * Cria um analisador para ler do terminal.
     */
    public Analisador() {
        comandos = new PalavrasComando();
        entrada = new Scanner(System.in);
    }

    /**
     * @return O proximo comando do usuario
     */
    public Comando getComando() {

        String inputLinha; // armazena uma linha inteira
        String palavra1 = null;
        String palavra2 = null;

        System.out.print("> "); // imprime o prompt

        inputLinha = entrada.nextLine();

        // Tenta encontrar ate duas palavras na linha
        Scanner tokenizer = new Scanner(inputLinha);
        if (tokenizer.hasNext()) {
            palavra1 = tokenizer.next(); // pega a primeira palavra
            if (tokenizer.hasNext()) {
                palavra2 = tokenizer.next(); // pega a segunda palavra
                // obs: nós simplesmente ignoramos o resto da linha.
            }
        }

        tokenizer.close();

        // Agora verifica se esta palavra eh conhecida. Se for, cria um comando
        // com ela. Se nao, cria um comando "null" (para comando desconhecido)
        if (comandos.ehComando(palavra1)) {
            return new Comando(palavra1, palavra2);
        } else {
            return new Comando(null, palavra2);
        }

    }

    /**
     * Imprime a lista de palavras de comandos válidas.
     */
    public void mostrarComandos() {
        comandos.mostrarTodos();
    }
}
