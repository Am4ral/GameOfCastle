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
    private JButton botaoIniciar;

    private ArrayList<ImageIcon> imagensMapa;
    private Jogo jogo;
    private Font fonteRotulo;
    private Font fonteValor;

    public InterfaceGrafica() {
        jogo = new Jogo();
        janela = new JFrame("GAME OF CASTLLE");
        fonteRotulo = new Font("Arial Bold", 0, 25);
        fonteValor = new Font("Arial", 0, 18);

        botaoEnviarFase1 = new JButton("Enviar");
        botaoEnviarFase2 = new JButton("Enviar");

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
                System.out.println(jogo.getItensAventureiro());
                if (retorno.contains("Você é incapaz de se defender...")) {
                    atualizarVida(jogo.getVidaAventureiro());
                    atualizarTextoJogo(imprimirComandoUsuario() + retorno + jogo.imprimirDerrota());
                    esperar();
                    encerrarJogo();
                } else if (retorno.contains("vitorioso")) {
                    atualizarTextoJogo(imprimirComandoUsuario() + retorno);
                    iniciarFase2();
                } else if (retorno.equals("sair")) {
                    ConfirmarSaida();
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
                String retorno = jogo.jogarFase2(getComandoUsuario());
                if (retorno.contains("morto")) {
                    atualizarTextoJogo(imprimirComandoUsuario() + retorno);
                    encerrarJogo();
                } else if (retorno.contains("portão se abre")) {
                    atualizarTextoJogo(imprimirComandoUsuario() + retorno);
                    iniciarFase3();
                } else if (retorno.equals("sair")) {
                    ConfirmarSaida();
                } else if (retorno.contains("porão")){
                    atualizarTextoJogo(imprimirComandoUsuario() + retorno);
                    atualizarTextoJogo(jogo.responderEnigma());
                    if(jogo.getVidaAventureiro() == 0){
                        encerrarJogo();
                    }
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

    private String imprimirComandoUsuario(){
        return "\n\n>> " + getComandoUsuario();
    }

    private void esperar() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    private void ConfirmarSaida() {
        limparCampoInput();
        if (JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja sair do jogo?", "Confirmação de Saída",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            atualizarTextoJogo("\nEncerrando...\nEncerrando...\nEncerrando...");
            encerrarJogo();
        }
    }

    private void encerrarJogo() {
        atualizarVida(0);
        esperar();
        JOptionPane.showMessageDialog(null, "Obrigado por jogar Game Of Castlle!");
        System.exit(0);
    }

    private void limparCampoInput() {
        inputUsuario.setText("");
    }

    private void atualizarVida(int valor) {
        valorVida.setText(String.valueOf(valor));
    }

    private void atualizarArma(String arma) {
        armaAventureiro.setText(arma);
    }

    private void atualizarNome(String arma) {
        valorNome.setText(arma);
    }

    private void atualizarTextoJogo(String texto) {
        textoJogo.append(texto);
    }

    private String getComandoUsuario() {
        return inputUsuario.getText();
    }

    private void removerItens() {
        painelItens.removeAll();
        painelDireita.remove(painelItens);
    }

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

    private void iniciarJogo() {
        painelBaixo.remove(botaoIniciar);
        painelBaixo.add(botaoEnviarFase1);
    }

    private void iniciarFase2() {
        imagemMapa.setIcon(new ImageIcon(imagensMapa.get(1).getImage().getScaledInstance(550, 500, 0)));
        tituloFase.setText("Fase 2");
        painelBaixo.remove(botaoEnviarFase1);
        painelBaixo.add(botaoEnviarFase2);
        jogo.criarCenarioFase2();
        JOptionPane.showMessageDialog(null, "Parabéns, você passou para a Fase 2!");
        atualizarTextoJogo(jogo.imprimirContextoFase2());
    }

    private void iniciarFase3(){
        imagemMapa.setIcon(new ImageIcon(imagensMapa.get(2).getImage().getScaledInstance(550, 500, 0)));
        tituloFase.setText("Fase 3");
        inputUsuario.setEnabled(false);
        botaoEnviarFase2.setEnabled(false);
        jogo.criarCenarioFase3();
        JOptionPane.showMessageDialog(null, "Parabéns, você passou para a Fase 3!");
        atualizarTextoJogo(jogo.imprimirContextoFase3() + jogo.interagirComMago());
        String retorno = jogo.finalizar();
        if(retorno.contains("Você está morto")){
            atualizarTextoJogo(retorno);
            encerrarJogo();
        }else{
            atualizarTextoJogo(retorno);
        }
    }

    private void mostrarTextoAtual() {
        textoJogo.setCaretPosition(textoJogo.getDocument().getLength());
    }

    public void exibirInterface() throws InterruptedException {
        janela.setResizable(false);
        janela.setVisible(true);

        atualizarTextoJogo(jogo.imprimirBoasVindas());

        jogo.personalizarAventureiro(JOptionPane.showInputDialog("Insira seu nome:"));

        botaoIniciar.setEnabled(true);
        inputUsuario.setEditable(true);

        atualizarTextoJogo(jogo.exibirMensagemArma());

        mostrarTextoAtual();
    }
}