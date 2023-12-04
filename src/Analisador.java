import java.util.Scanner;

/**
 * Esta classe é parte da aplicação "Game Of Castle".
 * 
 * Esse analisador lê a entrada do usuário e tenta interpretá-la. 
 * Cada vez que é chamado, ele lê uma linha do terminal e tenta 
 * interpretá-la como um comando de duas palavras. Ele retorna
 * o comando como um objeto da classe Comando.
 *
 * O analisador tem um conjunto de palavras de comando conhecidas. Ele compara
 * a entrada do usuário com os comandos conhecidos. Se a entrada nao é um
 * dos comandos conhecidos, ele retorna um objeto comando que é marcado como
 * um comando desconhecido.
 * 
 * @author Michael Kölling and David J. Barnes, João Pedro Ramalho e Renan Ribeiro
 * Pereira
 * @version 01.12.2023
 */
public class Analisador {
    private PalavrasComando comandos;

    /**
     * Cria um analisador para ler do terminal.
     */
    public Analisador() {
        comandos = new PalavrasComando();
    }

    /**
     * @return O próximo comando do usuário.
     */
    public Comando getComando(String inputLinha) {
        String palavra1 = null;
        String palavra2 = null;

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
     * @return A lista de palavras de comandos válidos.
     */
    public String mostrarComandos() {
        return comandos.mostrarTodos();
    }
}
