package com.petsync.telas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class LoginTela extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtSenha;

    public LoginTela() {
        setTitle("SnoutSync - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        getContentPane().setPreferredSize(new Dimension(1150, 720));
        getContentPane().setBackground(new Color(238, 242, 255));
        pack();
        setLocationRelativeTo(null);

        JPanelLogin painel = new JPanelLogin();
        painel.setBounds(20, 20, 1110, 680);
        painel.setLayout(null);
        add(painel);

        criarLadoEsquerdo(painel);
        criarFormulario(painel);
    }

    private void criarLadoEsquerdo(JPanelLogin painel) {
        JLabel logo = Navegacao.circle("P", 78, Color.WHITE, new Color(255, 255, 255, 45));
        logo.setBounds(170, 135, 150, 150);
        painel.add(logo);

        JLabel marca = Navegacao.label("SnoutSync", 50, Font.BOLD, Color.WHITE);
        marca.setHorizontalAlignment(SwingConstants.CENTER);
        marca.setBounds(80, 300, 330, 75);
        painel.add(marca);

        JLabel desc1 = Navegacao.label("Sistema de gestao para", 20, Font.PLAIN, Color.WHITE);
        desc1.setHorizontalAlignment(SwingConstants.CENTER);
        desc1.setBounds(80, 390, 330, 30);
        painel.add(desc1);

        JLabel desc2 = Navegacao.label("PetShop e Clinica Veterinaria", 20, Font.PLAIN, Color.WHITE);
        desc2.setHorizontalAlignment(SwingConstants.CENTER);
        desc2.setBounds(80, 420, 330, 30);
        painel.add(desc2);

        String[] itens = {"Agendamentos", "Clientes e Pets", "Servicos", "Financeiro"};
        for (int i = 0; i < itens.length; i++) {
            JLabel bola = Navegacao.circle(String.valueOf(i + 1), 18, Color.WHITE, new Color(255, 255, 255, 35));
            bola.setBounds(65 + (i * 105), 510, 62, 62);
            painel.add(bola);

            JLabel texto = Navegacao.label(itens[i], 13, Font.PLAIN, Color.WHITE);
            texto.setHorizontalAlignment(SwingConstants.CENTER);
            texto.setBounds(35 + (i * 105), 580, 120, 22);
            painel.add(texto);
        }
    }

    private void criarFormulario(JPanelLogin painel) {
        JLabel bemVindo = Navegacao.label("Bem-vindo de volta!", 22, Font.BOLD, Navegacao.AZUL);
        bemVindo.setBounds(590, 140, 340, 30);
        painel.add(bemVindo);

        JLabel titulo = Navegacao.label("Acesse sua conta", 42, Font.BOLD, Navegacao.TEXTO);
        titulo.setBounds(590, 180, 440, 56);
        painel.add(titulo);

        JLabel subtitulo = Navegacao.label("Entre para continuar gerenciando seu petshop.", 19, Font.PLAIN, Navegacao.TEXTO_SUAVE);
        subtitulo.setBounds(590, 244, 470, 30);
        painel.add(subtitulo);

        JLabel lblUsuario = Navegacao.label("Usuario", 16, Font.BOLD, Navegacao.TEXTO);
        lblUsuario.setBounds(590, 315, 200, 25);
        painel.add(lblUsuario);

        txtUsuario = Navegacao.campo("Digite seu usuario");
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        txtUsuario.setBounds(590, 350, 470, 58);
        placeholder(txtUsuario, "Digite seu usuario");
        painel.add(txtUsuario);

        JLabel lblSenha = Navegacao.label("Senha", 16, Font.BOLD, Navegacao.TEXTO);
        lblSenha.setBounds(590, 435, 200, 25);
        painel.add(lblSenha);

        txtSenha = new JPasswordField("Digite sua senha");
        txtSenha.setEchoChar((char) 0);
        txtSenha.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        txtSenha.setForeground(Navegacao.TEXTO_SUAVE);
        txtSenha.setBorder(txtUsuario.getBorder());
        txtSenha.setBounds(590, 470, 470, 58);
        placeholderSenha(txtSenha, "Digite sua senha");
        painel.add(txtSenha);

        JLabel esqueci = Navegacao.label("Esqueci minha senha", 15, Font.BOLD, Navegacao.AZUL);
        esqueci.setCursor(new Cursor(Cursor.HAND_CURSOR));
        esqueci.setBounds(590, 540, 200, 24);
        esqueci.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                recuperarSenha();
            }
        });
        painel.add(esqueci);

        JButton entrar = Navegacao.botaoPrimario("Entrar");
        entrar.setFont(new Font("Segoe UI", Font.BOLD, 22));
        entrar.setBounds(590, 600, 470, 62);
        entrar.addActionListener(e -> entrar());
        painel.add(entrar);
    }

    private void placeholder(JTextField campo, String texto) {
        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campo.getText().equals(texto)) {
                    campo.setText("");
                    campo.setForeground(Navegacao.TEXTO);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (campo.getText().trim().isEmpty()) {
                    campo.setText(texto);
                    campo.setForeground(Navegacao.TEXTO_SUAVE);
                }
            }
        });
    }

    private void placeholderSenha(JPasswordField campo, String texto) {
        campo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (new String(campo.getPassword()).equals(texto)) {
                    campo.setText("");
                    campo.setForeground(Navegacao.TEXTO);
                    campo.setEchoChar('*');
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (new String(campo.getPassword()).trim().isEmpty()) {
                    campo.setEchoChar((char) 0);
                    campo.setText(texto);
                    campo.setForeground(Navegacao.TEXTO_SUAVE);
                }
            }
        });
    }

    private void entrar() {
        String usuario = txtUsuario.getText().trim();
        String senha = new String(txtSenha.getPassword()).trim();

        if (usuario.isEmpty() || usuario.equals("Digite seu usuario")
                || senha.isEmpty() || senha.equals("Digite sua senha")) {
            JOptionPane.showMessageDialog(this, "Preencha usuario e senha.");
            return;
        }

        Navegacao.abrir(this, new DashboardTela());
    }

    private void recuperarSenha() {
        JOptionPane.showMessageDialog(this, "Procure o administrador do sistema para recuperar sua senha.");
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
        java.awt.EventQueue.invokeLater(() -> new LoginTela().setVisible(true));
    }

    private static class JPanelLogin extends javax.swing.JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 24, 24);
            java.awt.GradientPaint gp = new java.awt.GradientPaint(0, 0, Navegacao.AZUL_2, 0, getHeight(), Navegacao.AZUL);
            g2.setPaint(gp);
            g2.fillRoundRect(0, 0, 500, getHeight(), 24, 24);
            g2.fillRect(480, 0, 30, getHeight());
            g2.dispose();
        }
    }
}
