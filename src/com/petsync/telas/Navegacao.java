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
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class Navegacao {

    public static final int APP_W = 1200;
    public static final int APP_H = 720;
    public static final int MENU_W = 190;
    public static final int GAP = 18;

    public static final Color FUNDO = new Color(246, 248, 255);
    public static final Color AZUL = new Color(83, 111, 245);
    public static final Color AZUL_2 = new Color(111, 132, 250);
    public static final Color AZUL_CLARO = new Color(232, 237, 255);
    public static final Color MENU_ATIVO = new Color(132, 153, 255);
    public static final Color TEXTO = new Color(20, 33, 70);
    public static final Color TEXTO_SUAVE = new Color(100, 112, 145);
    public static final Color BORDA = new Color(224, 229, 242);
    public static final Color VERDE = new Color(34, 177, 99);
    public static final Color LARANJA = new Color(245, 143, 36);

    public static void configurarJanela(JFrame frame, String titulo) {
        frame.setTitle(titulo.replace("PetSync", "SnoutSync"));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(FUNDO);
        frame.getContentPane().setPreferredSize(new Dimension(APP_W, APP_H));
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public static void configurarModal(JFrame frame, String titulo) {
        frame.setTitle(titulo.replace("PetSync", "SnoutSync"));
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(FUNDO);
        frame.getContentPane().setPreferredSize(new Dimension(APP_W, APP_H));
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public static void abrir(JFrame atual, JFrame proxima) {
        proxima.setVisible(true);
        atual.dispose();
    }

    public static JPanel sidebar(JFrame atual, String ativa) {
        JPanel menu = new GradientPanel();
        menu.setLayout(null);
        menu.setBounds(0, 0, MENU_W, APP_H);

        JLabel logoIcon = circle("P", 54, Color.WHITE, AZUL);
        logoIcon.setBounds(65, 35, 60, 60);
        menu.add(logoIcon);

        JLabel nome = label("SnoutSync", 24, Font.BOLD, Color.WHITE);
        nome.setHorizontalAlignment(SwingConstants.CENTER);
        nome.setBounds(20, 100, 150, 34);
        menu.add(nome);

        JLabel sub = label("PetShop", 12, Font.PLAIN, Color.WHITE);
        sub.setHorizontalAlignment(SwingConstants.CENTER);
        sub.setBounds(20, 132, 150, 18);
        menu.add(sub);

        menu.add(menuItem("Dashboard", "Dashboard", ativa, 180, () -> abrir(atual, new DashboardTela())));
        menu.add(menuItem("Agendamentos", "Agendamentos", ativa, 230, () -> abrir(atual, new AgendamentosTela())));
        menu.add(menuItem("Clientes e Pets", "Clientes e Pets", ativa, 280, () -> abrir(atual, new ClientesTela())));
        menu.add(menuItem("Financeiro", "Financeiro", ativa, 330, () -> abrir(atual, new FinanceiroTela())));
        menu.add(menuItem("Configuracoes", "Configuracoes", ativa, 600, () -> mensagem(atual, "Sistema configurado para usar MySQL via JDBC.")));
        menu.add(menuItem("Sair", "Sair", ativa, 650, () -> abrir(atual, new LoginTela())));

        return menu;
    }

    private static JLabel menuItem(String texto, String id, String ativa, int y, Runnable acao) {
        JLabel item = new JLabel(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                if (id.equals(ativa)) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(MENU_ATIVO);
                    g2.fillRoundRect(16, 3, getWidth() - 32, getHeight() - 6, 10, 10);
                    g2.dispose();
                }
                super.paintComponent(g);
            }
        };
        item.setForeground(Color.WHITE);
        item.setFont(new Font("Segoe UI", Font.BOLD, 13));
        item.setBounds(0, y, MENU_W, 40);
        item.setBorder(BorderFactory.createEmptyBorder(0, 45, 0, 0));
        item.setCursor(new Cursor(Cursor.HAND_CURSOR));
        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!id.equals(ativa)) {
                    acao.run();
                }
            }
        });
        return item;
    }

    public static JPanel page(JFrame frame, String ativa, String titulo, String subtitulo) {
        configurarJanela(frame, "SnoutSync - " + titulo);
        frame.add(sidebar(frame, ativa));

        JPanel content = new JPanel(null);
        content.setOpaque(false);
        content.setBounds(MENU_W, 0, APP_W - MENU_W, APP_H);
        frame.add(content);

        JLabel title = label(titulo, 24, Font.BOLD, TEXTO);
        title.setBounds(28, 28, 360, 30);
        content.add(title);

        JLabel subtitle = label(subtitulo, 12, Font.PLAIN, TEXTO_SUAVE);
        subtitle.setBounds(28, 58, 400, 20);
        content.add(subtitle);

        JLabel avatar = circle("L", 34, Color.WHITE, AZUL);
        avatar.setBounds(860, 28, 38, 38);
        content.add(avatar);

        JLabel nome = label("Leonardo", 12, Font.BOLD, TEXTO);
        nome.setBounds(906, 28, 80, 18);
        content.add(nome);

        JLabel cargo = label("Administrador", 10, Font.PLAIN, TEXTO_SUAVE);
        cargo.setBounds(906, 45, 95, 18);
        content.add(cargo);

        return content;
    }

    public static JPanel card() {
        JPanel card = new RoundedPanel(14, Color.WHITE);
        card.setLayout(null);
        card.setBorder(BorderFactory.createLineBorder(BORDA));
        return card;
    }

    public static JPanel badge(String texto, Color corFundo, Color corTexto) {
        JPanel badge = new RoundedPanel(18, corFundo);
        badge.setLayout(null);
        JLabel lbl = label(texto, 11, Font.BOLD, corTexto);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setBounds(0, 2, 82, 20);
        badge.add(lbl);
        return badge;
    }

    public static JLabel label(String texto, int tamanho, int estilo, Color cor) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", estilo, tamanho));
        label.setForeground(cor);
        return label;
    }

    public static JLabel circle(String texto, int fonte, Color textoCor, Color fundo) {
        JLabel label = new JLabel(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(fundo);
                g2.fillOval(0, 0, getWidth(), getHeight());
                g2.dispose();
                super.paintComponent(g);
            }
        };
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setForeground(textoCor);
        label.setFont(new Font("Segoe UI", Font.BOLD, fonte));
        return label;
    }

    public static JTextField campo(String placeholder) {
        JTextField campo = new JTextField(placeholder);
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        campo.setForeground(TEXTO_SUAVE);
        campo.setBackground(Color.WHITE);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDA),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        return campo;
    }

    public static JButton botaoPrimario(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(AZUL);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public static JButton botaoSecundario(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(Color.WHITE);
        btn.setForeground(TEXTO);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBorder(BorderFactory.createLineBorder(BORDA));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public static void mensagem(JFrame frame, String texto) {
        javax.swing.JOptionPane.showMessageDialog(frame, texto);
    }

    public static class RoundedPanel extends JPanel {
        private final int radius;
        private final Color color;

        public RoundedPanel(int radius, Color color) {
            this.radius = radius;
            this.color = color;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private static class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            java.awt.GradientPaint gp = new java.awt.GradientPaint(0, 0, AZUL_2, 0, getHeight(), AZUL);
            g2.setPaint(gp);
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.dispose();
        }
    }
}
