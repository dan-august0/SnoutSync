package com.petsync.telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;

public class LoginTela extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getLogger(LoginTela.class.getName());

    // Cores padrão do sistema
    private static final Color COR_ROXO       = new Color(168, 177, 255);
    private static final Color COR_ROXO_BTN   = new Color(143, 162, 255);
    private static final Color COR_BRANCO      = Color.WHITE;
    private static final Color COR_PLACEHOLDER = new Color(180, 180, 180);
    private static final Color COR_TEXTO       = new Color(60, 60, 60);

    public LoginTela() {
        initComponents();
        configurarPlaceholders();
        setLocationRelativeTo(null); // centraliza na tela
    }

    private void configurarPlaceholders() {
        // Placeholder no campo usuário
        txtUsuario.setForeground(COR_PLACEHOLDER);
        txtUsuario.setText("username");
        txtUsuario.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtUsuario.getText().equals("username")) {
                    txtUsuario.setText("");
                    txtUsuario.setForeground(COR_TEXTO);
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (txtUsuario.getText().isEmpty()) {
                    txtUsuario.setText("username");
                    txtUsuario.setForeground(COR_PLACEHOLDER);
                }
            }
        });

        // Placeholder no campo senha
        char[] senhaVazia = {'p','a','s','s','w','o','r','d'};
        txtSenha.setEchoChar((char) 0);
        txtSenha.setText("password");
        txtSenha.setForeground(COR_PLACEHOLDER);
        txtSenha.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                String valor = new String(txtSenha.getPassword());
                if (valor.equals("password")) {
                    txtSenha.setText("");
                    txtSenha.setForeground(COR_TEXTO);
                    txtSenha.setEchoChar('•');
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (new String(txtSenha.getPassword()).isEmpty()) {
                    txtSenha.setEchoChar((char) 0);
                    txtSenha.setText("password");
                    txtSenha.setForeground(COR_PLACEHOLDER);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        // ── Painel esquerdo roxo ──────────────────────────────────────
        pnlEsquerdo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            }
        };
        pnlEsquerdo.setBackground(COR_ROXO);
        pnlEsquerdo.setLayout(null);

        lblPetSync = new JLabel("PetSync");
        lblPetSync.setFont(new Font("Segoe UI", Font.BOLD, 52));
        lblPetSync.setForeground(COR_BRANCO);
        lblPetSync.setBounds(100, 230, 300, 65);
        pnlEsquerdo.add(lblPetSync);

        lblSubtitulo = new JLabel("Pet e Shop");
        lblSubtitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblSubtitulo.setForeground(COR_BRANCO);
        lblSubtitulo.setBounds(155, 295, 200, 30);
        pnlEsquerdo.add(lblSubtitulo);

        // ── Painel direito branco ─────────────────────────────────────
        pnlDireito = new JPanel();
        pnlDireito.setBackground(COR_BRANCO);
        pnlDireito.setLayout(null);

        lblLogin = new JLabel("LOGIN");
        lblLogin.setFont(new Font("Segoe UI", Font.BOLD, 34));
        lblLogin.setForeground(COR_TEXTO);
        lblLogin.setBounds(180, 80, 200, 50);
        pnlDireito.add(lblLogin);

        lblUsuario = new JLabel("Usuário");
        lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblUsuario.setForeground(COR_TEXTO);
        lblUsuario.setBounds(90, 155, 200, 20);
        pnlDireito.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtUsuario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        txtUsuario.setBounds(90, 178, 280, 38);
        pnlDireito.add(txtUsuario);

        lblSenha = new JLabel("Senha");
        lblSenha.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblSenha.setForeground(COR_TEXTO);
        lblSenha.setBounds(90, 228, 200, 20);
        pnlDireito.add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        txtSenha.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        txtSenha.setBounds(90, 250, 280, 38);
        pnlDireito.add(txtSenha);

        btnEntrar = new JButton("Entrar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        btnEntrar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        btnEntrar.setBackground(COR_ROXO_BTN);
        btnEntrar.setForeground(COR_BRANCO);
        btnEntrar.setBorderPainted(false);
        btnEntrar.setContentAreaFilled(false);
        btnEntrar.setOpaque(false);
        btnEntrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEntrar.setBounds(90, 330, 280, 42);
        btnEntrar.addActionListener(this::btnEntrarActionPerformed);
        pnlDireito.add(btnEntrar);

        // ── Frame principal ───────────────────────────────────────────
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("PetSync - Login");
        setPreferredSize(new java.awt.Dimension(1000, 600));
        setResizable(false);
        getContentPane().setLayout(null);

        pnlEsquerdo.setBounds(0, 0, 460, 600);
        getContentPane().add(pnlEsquerdo);

        pnlDireito.setBounds(460, 0, 540, 600);
        getContentPane().add(pnlDireito);

        pack();
    }

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {
        String usuario = txtUsuario.getText().trim();
        String senha   = new String(txtSenha.getPassword()).trim();

        // Validação simples — substitua pela sua lógica de autenticação real
        if (usuario.isEmpty() || usuario.equals("username") ||
            senha.isEmpty()   || senha.equals("password")) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, preencha usuário e senha.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Abre o Dashboard e fecha o Login
        DashboardTela dashboard = new DashboardTela();
        dashboard.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new LoginTela().setVisible(true));
    }

    // ── Declaração de variáveis ───────────────────────────────────────
    private JPanel      pnlEsquerdo;
    private JPanel      pnlDireito;
    private JLabel      lblPetSync;
    private JLabel      lblSubtitulo;
    private JLabel      lblLogin;
    private JLabel      lblUsuario;
    private JLabel      lblSenha;
    private JTextField  txtUsuario;
    private JPasswordField txtSenha;
    private JButton     btnEntrar;
}
