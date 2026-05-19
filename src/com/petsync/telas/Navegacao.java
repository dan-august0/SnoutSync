package com.petsync.telas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Navegacao {

    public static final int LARGURA_MENU = 270;
    public static final int ALTURA_TOPO = 88;

    public static final Color FUNDO = new Color(238, 242, 255);
    public static final Color MENU = new Color(157, 174, 255);
    public static final Color MENU_ATIVO = new Color(174, 188, 255);
    public static final Color AZUL = new Color(91, 117, 255);
    public static final Color AZUL_CLARO = new Color(198, 208, 255);
    public static final Color TEXTO = new Color(45, 45, 60);
    public static final Color CINZA = new Color(243, 243, 243);

    public static void abrir(JFrame atual, JFrame proxima) {
        proxima.setVisible(true);
        atual.dispose();
    }

    public static void configurarJanela(JFrame frame, String titulo) {
        frame.setTitle(titulo);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(FUNDO);
    }

    public static JPanel criarSidebar(JFrame telaAtual, String telaAtiva) {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(null);
        sidebar.setBackground(MENU);
        sidebar.setBounds(0, 0, LARGURA_MENU, 600);

        JLabel logo = new JLabel("SnoutSync");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        logo.setForeground(Color.WHITE);
        logo.setBounds(80, 30, 160, 35);
        sidebar.add(logo);

        JLabel sub = new JLabel("Pet&shop");
        sub.setForeground(Color.WHITE);
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        sub.setBounds(118, 60, 100, 20);
        sidebar.add(sub);

        sidebar.add(itemMenu("Dashboard", 140, telaAtiva, () -> abrir(telaAtual, new DashboardTela())));
        sidebar.add(itemMenu("Agendamentos", 210, telaAtiva, () -> abrir(telaAtual, new AgendamentosTela())));
        sidebar.add(itemMenu("Clientes & Pets", 280, telaAtiva, () -> abrir(telaAtual, new ClientesTela())));
        sidebar.add(itemMenu("Financeiro", 350, telaAtiva, () -> abrir(telaAtual, new FinanceiroTela())));

        JButton novoCliente = botaoAzul("+ Novo cliente", 13);
        novoCliente.setBounds(70, 530, 120, 34);
        novoCliente.addActionListener(e -> new NovoClienteTela().setVisible(true));
        sidebar.add(novoCliente);

        return sidebar;
    }

    public static JLabel itemMenu(String texto, int y, String telaAtiva, Runnable acao) {
        JLabel label = new JLabel(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                if (texto.equals(telaAtiva)) {
                    g.setColor(MENU_ATIVO);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
                super.paintComponent(g);
            }
        };
        label.setOpaque(false);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 23));
        label.setBounds(0, y, LARGURA_MENU, 56);
        label.setBorder(BorderFactory.createEmptyBorder(0, 58, 0, 0));
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (!texto.equals(telaAtiva)) {
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    acao.run();
                }
            });
        }
        return label;
    }

    public static void adicionarTopo(JFrame tela, String titulo) {
        JPanel topo = new JPanel();
        topo.setLayout(null);
        topo.setBackground(Color.WHITE);
        topo.setBounds(LARGURA_MENU, 0, 730, ALTURA_TOPO);
        tela.add(topo);

        JLabel tituloLabel = new JLabel(titulo);
        tituloLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        tituloLabel.setForeground(AZUL);
        tituloLabel.setBounds(55, 33, 260, 26);
        topo.add(tituloLabel);

        JLabel circulo = new JLabel("AT");
        circulo.setHorizontalAlignment(JLabel.CENTER);
        circulo.setOpaque(true);
        circulo.setBackground(AZUL);
        circulo.setForeground(Color.WHITE);
        circulo.setFont(new Font("Segoe UI", Font.BOLD, 15));
        circulo.setBounds(610, 28, 35, 35);
        topo.add(circulo);

        JLabel atendente = new JLabel("Atendente");
        atendente.setForeground(AZUL);
        atendente.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        atendente.setBounds(650, 24, 110, 20);
        topo.add(atendente);

        JLabel pet = new JLabel("nome do pet");
        pet.setForeground(AZUL);
        pet.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        pet.setBounds(650, 42, 110, 20);
        topo.add(pet);
    }

    public static JPanel card(int raio) {
        JPanel painel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), raio, raio);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        painel.setOpaque(false);
        painel.setLayout(null);
        painel.setBackground(Color.WHITE);
        return painel;
    }

    public static JTextField campo(String texto) {
        JTextField campo = new JTextField(texto);
        campo.setBorder(BorderFactory.createEmptyBorder(6, 12, 6, 12));
        campo.setBackground(CINZA);
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        campo.setForeground(new Color(120, 120, 120));
        return campo;
    }

    public static JButton botaoAzul(String texto, int tamanho) {
        JButton botao = new JButton(texto);
        botao.setPreferredSize(new Dimension(120, 34));
        botao.setBackground(AZUL);
        botao.setForeground(Color.WHITE);
        botao.setBorderPainted(false);
        botao.setFocusPainted(false);
        botao.setFont(new Font("Segoe UI", Font.PLAIN, tamanho));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return botao;
    }

    public static JButton botaoCinza(String texto) {
        JButton botao = new JButton(texto);
        botao.setBackground(new Color(232, 232, 232));
        botao.setForeground(TEXTO);
        botao.setBorderPainted(false);
        botao.setFocusPainted(false);
        botao.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return botao;
    }
}
