package com.petsync.telas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;

public class AgendamentosTela extends JFrame {

    public AgendamentosTela() {

        setTitle("PetSync - Agendamentos");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        Color fundo = new Color(245, 247, 255);

        getContentPane().setBackground(fundo);

        // SIDEBAR
        JPanel sidebar = new JPanel();
        sidebar.setLayout(null);
        sidebar.setBackground(new Color(168, 177, 255));
        sidebar.setBounds(0, 0, 220, 600);
        add(sidebar);

        JLabel logo = new JLabel("PETSYNC");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        logo.setForeground(Color.WHITE);
        logo.setBounds(30, 30, 200, 40);
        sidebar.add(logo);

        JLabel subtitulo = new JLabel("Pet e Shop");
        subtitulo.setForeground(Color.WHITE);
        subtitulo.setBounds(70, 65, 100, 20);
        sidebar.add(subtitulo);

        JLabel dashboard = new JLabel("Dashboard");
        dashboard.setForeground(Color.WHITE);
        dashboard.setFont(new Font("Segoe UI", Font.BOLD, 22));
        dashboard.setBounds(25, 140, 170, 30);
        sidebar.add(dashboard);

        JLabel agendamentos = new JLabel("Agendamentos");
        agendamentos.setForeground(Color.WHITE);
        agendamentos.setFont(new Font("Segoe UI", Font.BOLD, 22));
        agendamentos.setBounds(25, 210, 200, 30);
        sidebar.add(agendamentos);

        JLabel clientes = new JLabel("Clientes & Pets");
        clientes.setForeground(Color.WHITE);
        clientes.setFont(new Font("Segoe UI", Font.BOLD, 22));
        clientes.setBounds(25, 280, 200, 30);
        sidebar.add(clientes);

        JLabel financeiro = new JLabel("Financeiro");
        financeiro.setForeground(Color.WHITE);
        financeiro.setFont(new Font("Segoe UI", Font.BOLD, 22));
        financeiro.setBounds(25, 350, 170, 30);
        sidebar.add(financeiro);

        // TITULO
        JLabel titulo = new JLabel("Agendamentos");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(new Color(74, 99, 255));
        titulo.setBounds(260, 30, 300, 40);
        add(titulo);

        // BOTAO NOVO
        JPanel botaoNovo = new JPanel();
        botaoNovo.setBackground(new Color(74, 99, 255));
        botaoNovo.setBounds(760, 30, 180, 40);
        botaoNovo.setLayout(null);

        JLabel textoBotao = new JLabel("+ Novo Agendamento");
        textoBotao.setForeground(Color.WHITE);
        textoBotao.setBounds(20, 10, 160, 20);
        textoBotao.addMouseListener(new java.awt.event.MouseAdapter() {

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {

        new NovoAgendamentoTela().setVisible(true);

    }
});

        botaoNovo.add(textoBotao);

        add(botaoNovo);

        // CAMPO BUSCA
        JTextField busca = new JTextField("Buscar...");
        busca.setBounds(260, 100, 300, 40);
        busca.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        add(busca);

        // CARD 1
        JPanel card1 = criarCard(
                "Thor - Banho",
                "10:00",
                "Confirmado",
                new Color(46, 204, 113)
        );

        card1.setBounds(260, 180, 680, 80);

        add(card1);

        // CARD 2
        JPanel card2 = criarCard(
                "Luna - Banho + Tosa",
                "11:30",
                "Pendente",
                new Color(241, 196, 15)
        );

        card2.setBounds(260, 280, 680, 80);

        add(card2);

        // CARD 3
        JPanel card3 = criarCard(
                "Mel - Tosa",
                "14:00",
                "Cancelado",
                new Color(231, 76, 60)
        );

        card3.setBounds(260, 380, 680, 80);

        add(card3);
    }

    private JPanel criarCard(String servico,
                             String horario,
                             String status,
                             Color corStatus) {

        JPanel card = new JPanel();

        card.setLayout(null);

        card.setBackground(Color.WHITE);

        card.setBorder(BorderFactory.createLineBorder(
                new Color(220, 220, 220)
        ));

        JLabel nome = new JLabel(servico);

        nome.setFont(new Font("Segoe UI", Font.BOLD, 18));

        nome.setBounds(20, 15, 300, 25);

        card.add(nome);

        JLabel hora = new JLabel(horario);

        hora.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        hora.setBounds(20, 45, 100, 20);

        card.add(hora);

        JPanel statusPanel = new JPanel();

        statusPanel.setBackground(corStatus);

        statusPanel.setBounds(520, 22, 120, 35);

        JLabel statusLabel = new JLabel(status);

        statusLabel.setForeground(Color.WHITE);

        statusPanel.add(statusLabel);

        card.add(statusPanel);

        return card;
    }

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {

            new AgendamentosTela().setVisible(true);

        });

    }
}