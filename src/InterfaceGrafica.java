import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.concurrent.TimeUnit;

public class InterfaceGrafica {
    private JFrame janela;
    private LineBorder bordaPaineis;

    private JLabel rotuloVida;
    private JLabel rotuloItens;
    private JLabel rotuloArma;

    private JLabel valorVida;
    private JLabel armaAventureiro;

    private JPanel painelEsquerda;
    private JPanel painelDireita;
    private JLabel painelCentro;
    private JPanel painelBaixo;

    private JLabel imagemMapa;
    private JLabel tituloFase;

    private JTextArea textoJogo;
    private JTextArea inputUsuario;

    private JButton botaoEnviar;
    private JButton botaoIniciar;

    private ArrayList<ImageIcon> imagensMapa;
    private Jogo jogo;

    public InterfaceGrafica() {
        jogo = new Jogo();
        janela = new JFrame("GAME OF CASTLLE");

        botaoEnviar = new JButton("Enviar");

        botaoIniciar = new JButton("Iniciar");
        botaoIniciar.setEnabled(false);

        rotuloVida = new JLabel("Vida");
        valorVida = new JLabel("valor da vida");
        rotuloItens = new JLabel("Itens");
        rotuloArma = new JLabel("Arma");

        armaAventureiro = new JLabel("");
        painelCentro = new JLabel();
        imagemMapa = new JLabel();
        tituloFase = new JLabel("Fase 1");

        inputUsuario = new JTextArea("");
        inputUsuario.setEditable(false);
        inputUsuario.setColumns(150);

        textoJogo = new JTextArea("");
        textoJogo.setColumns(150);
        textoJogo.setLineWrap(true);
        textoJogo.setEditable(false);

        painelEsquerda = new JPanel();
        painelDireita = new JPanel();
        painelBaixo = new JPanel();

        imagensMapa = new ArrayList<>();

        bordaPaineis = new LineBorder(Color.black, 2);

        imagensMapa.add(new ImageIcon("assets/MapaFase1.png"));
        imagensMapa.add(new ImageIcon("assets/MapaFase2.png"));
        imagensMapa.add(new ImageIcon("assets/MapaFase3.png"));

        botaoEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jogo.getRespostaInterface(getMensagemUsuario());
                atualizarArma(jogo.getArmaAventureiro());
                inputUsuario.setText("");
            }
        });

        botaoIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jogo.getRespostaInterface(getMensagemUsuario());
                atualizarArma(jogo.getArmaAventureiro());
                atualizarVida(jogo.getVidaAventureiro());
                inputUsuario.setText("");
                iniciarJogo();
            }
        });

        montarInterface();
        configurarListeners();
    }

    public void montarInterface() {
        janela.setLayout(new BorderLayout());
        janela.setSize(1200, 700);

        // esquerda
        painelEsquerda.setLayout(new GridLayout(2, 1));
        painelEsquerda.setPreferredSize(new Dimension(250, 0));
        painelEsquerda.add(rotuloVida);
        painelEsquerda.add(valorVida);
        painelEsquerda.add(rotuloArma);
        painelEsquerda.add(armaAventureiro);
        rotuloVida.setHorizontalAlignment(JLabel.CENTER);
        rotuloArma.setHorizontalAlignment(JLabel.CENTER);
        painelEsquerda.setBorder(bordaPaineis);

        // direita
        painelDireita.setPreferredSize(new Dimension(250, 0));
        painelDireita.add(rotuloItens);
        rotuloItens.setHorizontalAlignment(JLabel.CENTER);
        painelDireita.setBorder(bordaPaineis);

        // centro
        painelCentro.setLayout(new BorderLayout());
        imagemMapa.setIcon(imagensMapa.get(0));
        imagemMapa.setHorizontalAlignment(JLabel.CENTER);
        painelCentro.add(imagemMapa, BorderLayout.CENTER);
        tituloFase.setHorizontalAlignment(JLabel.CENTER);
        painelCentro.add(tituloFase, BorderLayout.SOUTH);

        painelCentro.setBorder(bordaPaineis);

        // baixo
        painelBaixo.setPreferredSize(new Dimension(0, 200));
        painelBaixo.setBorder(bordaPaineis);
        painelBaixo.add(textoJogo);
        painelBaixo.add(inputUsuario);
        painelBaixo.add(botaoIniciar);

        // adiciionando paineis na janela
        janela.add(painelEsquerda, BorderLayout.WEST);
        janela.add(painelDireita, BorderLayout.EAST);
        janela.add(painelCentro, BorderLayout.CENTER);
        janela.add(painelBaixo, BorderLayout.SOUTH);

        janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ajustarTamanhoImagem();
    }

    private void configurarListeners() {
        janela.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                ajustarTamanhoImagem();
            }
        });
    }

    private void ajustarTamanhoImagem() {
        int larguraJanela = janela.getWidth();
        int alturaJanela = janela.getHeight();

        ImageIcon imagemOriginal = imagensMapa.get(0);

        int larguraOriginal = imagemOriginal.getIconWidth();
        int alturaOriginal = imagemOriginal.getIconHeight();

        double escalaLargura = (double) larguraJanela / larguraOriginal;
        double escalaAltura = (double) alturaJanela / alturaOriginal;
        double escala = Math.min(escalaLargura, escalaAltura);

        int novaLargura = (int) (larguraOriginal * escala);
        int novaAltura = (int) (alturaOriginal * escala);

        // redimensiona a imagem
        Image imagemRedimensionada = imagemOriginal.getImage().getScaledInstance(novaLargura, novaAltura,
                Image.SCALE_SMOOTH);

        // atualiza o ícone do painelCentro com a nova imagem redimensionada
        imagemMapa.setIcon(new ImageIcon(imagemRedimensionada));
    }

    private void atualizarVida(int valor) {
        valorVida.setText(String.valueOf(valor));
    }

    private void atualizarArma(String arma) {
        armaAventureiro.setText(arma);
    }

    private void atualizarTextoJogo(String texto) {
        textoJogo.setText(texto);
    }

    private String getMensagemUsuario() {
        return inputUsuario.getText();
    }

    private void iniciarJogo() {
        painelBaixo.remove(botaoIniciar);
        painelBaixo.add(botaoEnviar);

    }

    public void exibirInterface() throws InterruptedException {
        janela.setVisible(true);
        atualizarTextoJogo(jogo.imprimirBoasVindas());
        TimeUnit.SECONDS.sleep(5);

        inputUsuario.setEditable(true);
        botaoIniciar.setEnabled(true);

        atualizarTextoJogo(jogo.exibirMensagemArma());

        // jogo.imprimirContextoInicial();
        // TimeUnit.SECONDS.sleep(10);
    }

}