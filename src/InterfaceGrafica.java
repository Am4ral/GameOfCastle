import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Classe Ambiente - um ambiente em um jogo adventure.
 *
 * Esta classe eh parte da aplicacao "Game Of Castle".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.
 *
 * Um "ambientes.Ambiente" representa uma localizacao no cenario do jogo. Ele eh
 * conectado aos outros ambientes atraves de saidas. As saidas sao
 * nomeadas como norte, sul, leste e oeste. Para cada direcao, o ambiente
 * guarda uma referencia para o ambiente vizinho, ou null se nao ha
 * saida naquela direcao.
 * 
 * @author Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @author João Pedro, Renan
 * @version 2011.07.31 (2016.02.01)
 */

public class InterfaceGrafica {
    private JFrame janela;
    private LineBorder bordaPaineis;
    private JLabel rotuloVida;
    private JLabel rotuloItens;
    private JLabel rotuloArma;
    private JLabel rotuloNome;
    private JLabel valorVida;
    private JLabel valorNome;
    private JLabel armaAventureiro;
    private JPanel painelEsquerda;
    private JPanel painelDireita;
    private JLabel painelCentro;
    private JPanel painelBaixo;
    private JPanel painelItens;
    private JLabel imagemMapa;
    private JLabel tituloFase;
    private JScrollPane painelTexto;
    private JTextArea textoJogo;
    private JTextField inputUsuario;
    private JLabel rotuloInserirResposta;
    private JButton botaoEnviarFase1;
    private JButton botaoEnviarFase2;
    private JButton botaoEnviarFase3;
    private JButton botaoIniciar;
    private ArrayList<ImageIcon> imagensMapa;
    private Jogo jogo;
    private Font fonteRotulo;
    private Font fonteValor;
    private ImageIcon imagemIconeFinal;
    private ImageIcon imagemIconePocao;
    private ImageIcon imagemIconeCastelo;
    private ImageIcon imagemIconeAventureiro;
    private ImageIcon imagemIconeChave;
    private ImageIcon imagemIconeMago;
    private ImageIcon iconeArma;

    /**
     * Inicia todas as telas, textos, imagens, eventos de botões,
     * fontes, o Jogo, e outros itens necessários para a interface gráfica.
     */
    public InterfaceGrafica() {
        jogo = new Jogo();
        janela = new JFrame("GAME OF CASTLLE");
        fonteRotulo = new Font("Arial Bold", 0, 25);
        fonteValor = new Font("Arial", 0, 18);

        botaoEnviarFase1 = new JButton("Enviar");
        botaoEnviarFase2 = new JButton("Enviar");
        botaoEnviarFase3 = new JButton("Enviar");

        botaoIniciar = new JButton("Iniciar");
        botaoIniciar.setEnabled(false);

        rotuloVida = new JLabel("Vida");
        rotuloNome = new JLabel("Aventureiro");
        valorVida = new JLabel("");
        valorNome = new JLabel("");
        rotuloItens = new JLabel("Itens");
        rotuloArma = new JLabel("Arma");

        armaAventureiro = new JLabel("");
        painelCentro = new JLabel();
        imagemMapa = new JLabel();
        imagemMapa.setSize(new Dimension(500, 500));
        imagemMapa.setBackground(new Color(16));
        tituloFase = new JLabel("Fase 1");

        inputUsuario = new JTextField();
        inputUsuario.setEditable(false);
        inputUsuario.setColumns(122);

        textoJogo = new JTextArea("");
        painelTexto = new JScrollPane(textoJogo);
        painelTexto.setPreferredSize(new Dimension(1350, 120));
        textoJogo.setLineWrap(true);
        textoJogo.setEditable(false);

        rotuloInserirResposta = new JLabel("Digite aqui sua resposta:");
        rotuloInserirResposta.setFont(new Font("Arial Bold", 0, 13));

        rotuloItens = new JLabel("Itens");

        painelEsquerda = new JPanel();
        painelDireita = new JPanel();

        painelBaixo = new JPanel();
        painelItens = new JPanel();

        imagensMapa = new ArrayList<>();

        bordaPaineis = new LineBorder(Color.black, 2);

        imagensMapa.add(new ImageIcon("../assets/MapaFase1.png"));
        imagensMapa.add(new ImageIcon("../assets/MapaFase2.png"));
        imagensMapa.add(new ImageIcon("../assets/MapaFase3.png"));

        ImageIcon imagemIcone = new ImageIcon("../assets/CoracaoDeGelo2.png");
        imagemIconeFinal = new ImageIcon(imagemIcone.getImage().getScaledInstance(60, 60, 0));
        imagemIcone = new ImageIcon("../assets/Pocao.png");
        imagemIconePocao = new ImageIcon(imagemIcone.getImage().getScaledInstance(60, 60, 0));
        imagemIcone = new ImageIcon("../assets/Castelo.png");
        imagemIconeCastelo = new ImageIcon(imagemIcone.getImage().getScaledInstance(60, 60, 0));
        imagemIcone = new ImageIcon("../assets/Aventureiro.png");
        imagemIconeAventureiro = new ImageIcon(imagemIcone.getImage().getScaledInstance(60, 60, 0));
        imagemIcone = new ImageIcon("../assets/Chave.png");
        imagemIconeChave = new ImageIcon(imagemIcone.getImage().getScaledInstance(60, 60, 0));
        imagemIcone = new ImageIcon("../assets/Mago.png");
        imagemIconeMago = new ImageIcon(imagemIcone.getImage().getScaledInstance(60, 60, 0));

        botaoIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensagemRetorno = jogo.setArmaAventureiro(getComandoUsuario());
                if (mensagemRetorno.equals("")) {
                    atualizarArma(jogo.getArmaAventureiro());
                    atualizarVida(jogo.getVidaAventureiro());
                    atualizarNome(jogo.getNomeAventureiro());
                    atualizarItens(jogo.getItensAventureiro());
                    atualizarTextoJogo(jogo.imprimirContextoInicial());
                    iniciarJogo();
                } else {
                    atualizarTextoJogo(mensagemRetorno);
                }
                limparCampoInput();
            }
        });

        botaoEnviarFase1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String retorno = jogo.jogarFase1(getComandoUsuario());
                if (retorno.contains("Você é incapaz de se defender...")) {
                    atualizarVida(jogo.getVidaAventureiro());
                    atualizarTextoJogo(imprimirComandoUsuario() + retorno + jogo.imprimirDerrota());
                    atualizarVida(0);
                    encerrarJogo();
                } else if (retorno.contains("vitorioso")) {
                    atualizarTextoJogo(imprimirComandoUsuario() + retorno);
                    iniciarFase2();
                } else if (retorno.equals("sair")) {
                    confirmarSaida();
                } else {
                    atualizarTextoJogo(imprimirComandoUsuario() + retorno);
                }

                atualizarItens(jogo.getItensAventureiro());
                limparCampoInput();
            }
        });

        botaoEnviarFase2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String retorno = jogo.tratarComando(getComandoUsuario());
                if (retorno.contains("morto")) {
                    atualizarTextoJogo(imprimirComandoUsuario() + retorno);
                    atualizarVida(0);
                    encerrarJogo();
                } else if (retorno.contains("portão se abre")) {
                    atualizarItens(jogo.getItensAventureiro());
                    atualizarTextoJogo(imprimirComandoUsuario() + retorno);
                    iniciarFase3();
                } else if (retorno.equals("sair")) {
                    confirmarSaida();
                } else if (retorno.contains("porão")) {
                    atualizarTextoJogo(imprimirComandoUsuario() + retorno);
                    String enigma = jogo.gerarEnigma();
                    String resposta = JOptionPane.showInputDialog(null,
                            enigma + "\n\nNão utilize acentuação e escreva no singular!", "Enigma Esfinge", 3);
                    if (resposta == null) {
                        jogo.finalizarJogador();
                        resposta = "";
                    }
                    atualizarTextoJogo(jogo.analisarRespostaEnigmaEsfinge(resposta, enigma));
                    if (jogo.getVidaAventureiro() == 0) {
                        atualizarVida(jogo.getVidaAventureiro());
                        encerrarJogo();
                    }
                    JOptionPane.showMessageDialog(null, "Parabéns, você acertou!", "Acerto", 1, imagemIconeChave);
                } else {
                    atualizarTextoJogo(imprimirComandoUsuario() + retorno);
                }

                atualizarVida(jogo.getVidaAventureiro());
                atualizarItens(jogo.getItensAventureiro());
                limparCampoInput();
            }
        });

        botaoEnviarFase3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String retorno = jogo.tratarComando(getComandoUsuario());
                if (retorno.contains("Você está morto")) {
                    atualizarTextoJogo(retorno);
                    atualizarVida(0);
                    encerrarJogo();
                } else if (retorno.equals("sair")) {
                    confirmarSaida();
                } else if (retorno.contains("coração")) {
                    atualizarTextoJogo(retorno);
                    atualizarItens(jogo.getItensAventureiro());
                    limparCampoInput();
                    JOptionPane.showMessageDialog(null, "Parabéns, você derrotou o Dragão e venceu o jogo!", "Vitória",
                            1, imagemIconeFinal);
                    encerrarJogo();
                } else {
                    atualizarTextoJogo(imprimirComandoUsuario() + retorno);
                }

                atualizarVida(jogo.getVidaAventureiro());
                atualizarItens(jogo.getItensAventureiro());
                limparCampoInput();
            }
        });

        montarInterface();
    }

    /**
     * Cria cada um dos painéis presentes na interface.
     * Painéis de item, de input e texto, e de status do jogador.
     */
    public void montarInterface() {
        janela.setLayout(new BorderLayout());
        janela.setSize(1200, 700);

        // esquerda
        painelEsquerda.setLayout(new GridLayout(6, 1));
        painelEsquerda.setPreferredSize(new Dimension(250, 0));
        painelEsquerda.setBackground(new Color(159, 182, 205));
        painelEsquerda.add(rotuloNome);
        painelEsquerda.add(valorNome);
        painelEsquerda.add(rotuloVida);
        painelEsquerda.add(valorVida);
        painelEsquerda.add(rotuloArma);
        painelEsquerda.add(armaAventureiro);

        rotuloNome.setHorizontalAlignment(JLabel.CENTER);
        valorNome.setHorizontalAlignment(JLabel.CENTER);
        rotuloVida.setHorizontalAlignment(JLabel.CENTER);
        valorVida.setHorizontalAlignment(JLabel.CENTER);
        rotuloArma.setHorizontalAlignment(JLabel.CENTER);
        armaAventureiro.setHorizontalAlignment(JLabel.CENTER);

        valorNome.setVerticalAlignment(JLabel.NORTH);
        valorVida.setVerticalAlignment(JLabel.NORTH);
        armaAventureiro.setVerticalAlignment(JLabel.NORTH);

        rotuloVida.setFont(fonteRotulo);
        rotuloNome.setFont(fonteRotulo);
        rotuloArma.setFont(fonteRotulo);
        valorVida.setFont(fonteValor);
        valorNome.setFont(fonteValor);
        armaAventureiro.setFont(fonteValor);

        painelEsquerda.setBorder(bordaPaineis);

        // direita
        painelDireita.setLayout(new BoxLayout(painelDireita, BoxLayout.Y_AXIS));
        painelItens.setLayout(new BoxLayout(painelItens, BoxLayout.Y_AXIS));
        painelItens.setBackground(new Color(159, 182, 205));
        painelDireita.setBackground(new Color(159, 182, 205));
        rotuloItens.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelDireita.setPreferredSize(new Dimension(250, 0));
        painelDireita.add(rotuloItens);
        painelDireita.setBorder(bordaPaineis);
        painelDireita.add(painelItens);
        rotuloItens.setFont(fonteRotulo);

        // centro
        painelCentro.setLayout(new BorderLayout());
        imagemMapa.setIcon(new ImageIcon(imagensMapa.get(0).getImage().getScaledInstance(550, 550, 0)));
        imagemMapa.setHorizontalAlignment(JLabel.CENTER);
        painelCentro.add(imagemMapa, BorderLayout.CENTER);
        tituloFase.setHorizontalAlignment(JLabel.CENTER);
        tituloFase.setFont(fonteValor);
        painelCentro.add(tituloFase, BorderLayout.SOUTH);
        painelCentro.setBorder(bordaPaineis);

        // baixo
        painelBaixo.setPreferredSize(new Dimension(0, 210));
        painelBaixo.setBorder(bordaPaineis);
        painelBaixo.add(painelTexto);
        painelBaixo.add(rotuloInserirResposta);
        painelBaixo.add(inputUsuario);
        painelBaixo.add(botaoIniciar);

        // adicionando paineis na janela
        janela.add(painelEsquerda, BorderLayout.WEST);
        janela.add(painelDireita, BorderLayout.EAST);
        janela.add(painelCentro, BorderLayout.CENTER);
        janela.add(painelBaixo, BorderLayout.SOUTH);

        janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Formata o comando digitado pelo usuário para ser futuramente
     * exibido na área de texto.
     * 
     * @return String contendo o comando digitado pelo usuário
     */
    private String imprimirComandoUsuario() {
        return "\n\n>> " + getComandoUsuario();
    }

    /**
     * Pausa o programa por 500ms.
     */
    private void esperar() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Mostra uma tela perguntando se o jogador realmente deseja fechar o jogo
     * quando
     * ele digita a opção "sair".
     */
    private void confirmarSaida() {
        limparCampoInput();
        if (JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja sair do jogo?", "Confirmação de Saída",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            atualizarTextoJogo("\nEncerrando...\nEncerrando...\nEncerrando...");
            encerrarJogo();
        }
    }

    /**
     * Finaliza o jogo, salvando a camapanha do jogador em um arquivo de log.
     */
    private void encerrarJogo() {
        JOptionPane.showMessageDialog(null,
                "Vamos salvar a sua campanha em um arquivo de log!\nO arquivo \"arquivoLog\" será salvo na pasta do jogo.",
                "Arquivo", 1);
        try (FileWriter arq = new FileWriter("arquivoLog")) {
            arq.write(textoJogo.getText());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Falha ao salvar arquivo!", "Falha", JOptionPane.ERROR_MESSAGE);
        }
        esperar();
        JOptionPane.showMessageDialog(null, "Obrigado por jogar Game Of Castlle!", "Agradecimento", 1,
                imagemIconeCastelo);
        System.exit(0);
    }

    /**
     * Limpa o campo de input para que ele possa receber a próxima instrução.
     */
    private void limparCampoInput() {
        inputUsuario.setText("");
    }

    /**
     * Atualiza a vida do jogador após ele receber dano ou se curado.
     * 
     * @param int correspondende ao valor que deve ser retirado ou adicionado à
     *            vida.
     */
    private void atualizarVida(int valor) {
        valorVida.setText(String.valueOf(valor));
    }

    /**
     * Atualiza o ícone da arma, que aparece no painel do jogador,
     * após ele selecionar uma durante o começo do jogo.
     */
    private void atualizarArma(String arma) {
        if (arma.equals("Revólver")) {
            iconeArma = new ImageIcon("../assets/Revolver.png");
            armaAventureiro.setIcon(new ImageIcon(iconeArma.getImage().getScaledInstance(80, 80, 0)));
        } else if (arma.equals("Estilingue")) {
            iconeArma = new ImageIcon("../assets/Estilingue.png");
            armaAventureiro.setIcon(new ImageIcon(iconeArma.getImage().getScaledInstance(80, 80, 0)));
        } else if (arma.equals("Espada")) {
            iconeArma = new ImageIcon("../assets/Espada.png");
            armaAventureiro.setIcon(new ImageIcon(iconeArma.getImage().getScaledInstance(80, 80, 0)));
        } else if (arma.equals("Arco")) {
            iconeArma = new ImageIcon("../assets/Arco.png");
            armaAventureiro.setIcon(new ImageIcon(iconeArma.getImage().getScaledInstance(80, 80, 0)));
        }
    }

    /**
     * Atualiza o nome do jogador, que aparece no painel do jogador,
     * após ele digitar um durante o começo do jogo.
     */
    private void atualizarNome(String arma) {
        valorNome.setText(arma);
    }

    /**
     * Atualiza o texto que está sendo exibido no painél de texto.
     */
    private void atualizarTextoJogo(String texto) {
        textoJogo.append(texto);
    }

    /**
     * Recebe o comando que o jogador escreveu no painel de input.
     */
    private String getComandoUsuario() {
        return inputUsuario.getText();
    }

    /**
     * Remove todos os itens do painel de itens do jogaodr.
     */
    private void removerItens() {
        painelItens.removeAll();
        painelDireita.remove(painelItens);
    }

    /**
     * Atualiza os dados do painel de itens do jogador.
     */
    private void atualizarItens(List<Item> itens) {
        removerItens();
        JLabel itemLabel;

        for (Item item : itens) {
            itemLabel = new JLabel(item.getNome());
            itemLabel.setFont(fonteValor);
            itemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            painelItens.add(itemLabel);
        }

        painelDireita.add(painelItens);
        painelDireita.revalidate();
        painelDireita.repaint();
    }

    /**
     * Troca o botão que inicia o jogo para o botão de enviar comandos da fase 1.
     */
    private void iniciarJogo() {
        painelBaixo.remove(botaoIniciar);
        painelBaixo.add(botaoEnviarFase1);
    }

    /**
     * Troca o botão de enviar comandos da fase 1 o jogo para o botão de enviar
     * comandos da fase 2.
     * E prepara a tela com as informações da fase 2.
     */
    private void iniciarFase2() {
        imagemMapa.setIcon(new ImageIcon(imagensMapa.get(1).getImage().getScaledInstance(550, 500, 0)));
        tituloFase.setText("Fase 2");
        painelBaixo.remove(botaoEnviarFase1);
        painelBaixo.add(botaoEnviarFase2);
        JOptionPane.showMessageDialog(null, "Você passou para a Fase 2!", "Parabéns", 1, imagemIconeCastelo);
        atualizarTextoJogo(jogo.imprimirContextoFase2());
    }

    /**
     * Troca o botão de enviar comandos da fase 2 o jogo para o botão de enviar
     * comandos da fase 3.
     * E imprime exie os painéis de interação com o NPC da fase
     */
    private void iniciarFase3() {
        imagemMapa.setIcon(new ImageIcon(imagensMapa.get(2).getImage().getScaledInstance(550, 500, 0)));
        tituloFase.setText("Fase 3");
        painelBaixo.remove(botaoEnviarFase2);
        painelBaixo.add(botaoEnviarFase3);
        JOptionPane.showMessageDialog(null, "Você passou para a Fase 3!", "Parabéns", 1, imagemIconeCastelo);
        atualizarTextoJogo(jogo.imprimirContextoFase3());
        String enigma = "";
        String resposta = "";
        if (JOptionPane.showConfirmDialog(null, "Gostaria de jogar um jogo comigo?", "Mago Mershmann",
                JOptionPane.YES_NO_OPTION, 3, imagemIconeMago) == JOptionPane.YES_OPTION) {
            enigma = jogo.gerarEnigma();
            resposta = JOptionPane.showInputDialog(null, "Que bom aventureiro! Eis a sua pergunta: \n\n" + enigma
                    + "\n\nNão utilize acentuação e digite apenas uma palavra!", "Mago Mershmann", 3);
        }
        atualizarTextoJogo(jogo.analisarRespostaEnigmaDoMago(resposta, enigma));
    }

    /**
     * Realiza um scroll down na área de texto do painél.
     */
    private void mostrarTextoAtual() {
        textoJogo.setCaretPosition(textoJogo.getDocument().getLength());
    }

    /**
     * Inicializa a interface e o jogo.
     */
    public void exibirInterface() throws InterruptedException {
        janela.setResizable(false);
        janela.setVisible(true);

        atualizarTextoJogo(jogo.imprimirBoasVindas());

        jogo.personalizarAventureiro((String) JOptionPane.showInputDialog(null, "Aventureiro, informe o seu nome:",
                "Nome", 1, imagemIconeAventureiro, null, ""));

        botaoIniciar.setEnabled(true);
        inputUsuario.setEditable(true);

        atualizarTextoJogo(jogo.exibirMensagemArma());

        mostrarTextoAtual();
    }
}