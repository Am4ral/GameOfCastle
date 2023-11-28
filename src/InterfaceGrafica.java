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
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// import java.util.concurrent.TimeUnit;

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
    private JTextArea inputUsuario;

    private JButton botaoEnviar;
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

        botaoEnviar = new JButton("Enviar");

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
        tituloFase = new JLabel("Fase 1");

        inputUsuario = new JTextArea("");
        inputUsuario.setEditable(false);
        inputUsuario.setColumns(150);

        textoJogo = new JTextArea("");

        painelTexto = new JScrollPane(textoJogo);
        painelTexto.setPreferredSize(new Dimension(1500, 130));

        textoJogo.setLineWrap(true);
        textoJogo.setEditable(false);

        painelEsquerda = new JPanel();
        painelDireita = new JPanel();

        painelBaixo = new JPanel();
        painelItens = new JPanel();

        imagensMapa = new ArrayList<>();

        bordaPaineis = new LineBorder(Color.black, 2);

        imagensMapa.add(new ImageIcon("assets/MapaFase1.png"));
        imagensMapa.add(new ImageIcon("assets/MapaFase2.png"));
        imagensMapa.add(new ImageIcon("assets/MapaFase3.png"));

        botaoEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarTextoJogo(jogo.jogar(getComandoUsuario()));
                atualizarItens(jogo.getItensAventureiro());
                inputUsuario.setText("");
            }
        });

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
                inputUsuario.setText("");
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

        rotuloItens.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelDireita.setPreferredSize(new Dimension(250, 0));
        painelDireita.add(rotuloItens);
        painelDireita.setBorder(bordaPaineis);
        painelDireita.add(painelItens);

        rotuloItens.setFont(fonteRotulo);

        // centro
        painelCentro.setLayout(new BorderLayout());
        imagemMapa.setIcon(imagensMapa.get(0));
        imagemMapa.setHorizontalAlignment(JLabel.CENTER);

        imagemMapa
                .setIcon(new ImageIcon(imagensMapa.get(0).getImage().getScaledInstance(700, 700, Image.SCALE_SMOOTH)));
        painelCentro.add(imagemMapa, BorderLayout.CENTER);
        tituloFase.setHorizontalAlignment(JLabel.CENTER);
        tituloFase.setFont(fonteValor);
        painelCentro.add(tituloFase, BorderLayout.SOUTH);

        painelCentro.setBorder(bordaPaineis);

        // baixo
        painelBaixo.setPreferredSize(new Dimension(0, 210));
        painelBaixo.setBorder(bordaPaineis);
        painelBaixo.add(painelTexto);
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
    }

    private void iniciarJogo() {
        painelBaixo.remove(botaoIniciar);
        painelBaixo.add(botaoEnviar);
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