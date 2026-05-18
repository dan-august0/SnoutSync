package com.petsync.telas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientesTela extends JFrame {

    public ClientesTela() {

        setTitle("PetSync - Clientes");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        getContentPane().setBackground(new Color(245,247,255));

        // SIDEBAR
        JPanel sidebar = new JPanel();
        sidebar.setLayout(null);
        sidebar.setBackground(new Color(168,177,255));
        sidebar.setBounds(0,0,220,600);

        add(sidebar);

        JLabel logo = new JLabel("PETSYNC");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        logo.setForeground(Color.WHITE);
        logo.setBounds(30,30,200,40);

        sidebar.add(logo);

        JLabel sub = new JLabel("Pet e Shop");
        sub.setForeground(Color.WHITE);
        sub.setBounds(70,65,100,20);

        sidebar.add(sub);

        JLabel dash = new JLabel("Dashboard");
        dash.setForeground(Color.WHITE);
        dash.setFont(new Font("Segoe UI", Font.BOLD, 22));
        dash.setBounds(25,140,200,30);

        sidebar.add(dash);

        JLabel agenda = new JLabel("Agendamentos");
        agenda.setForeground(Color.WHITE);
        agenda.setFont(new Font("Segoe UI", Font.BOLD, 22));
        agenda.setBounds(25,210,200,30);

        sidebar.add(agenda);

        JLabel clientes = new JLabel("Clientes & Pets");
        clientes.setForeground(Color.WHITE);
        clientes.setFont(new Font("Segoe UI", Font.BOLD, 22));
        clientes.setBounds(25,280,220,30);

        sidebar.add(clientes);

        JLabel financeiro = new JLabel("Financeiro");
        financeiro.setForeground(Color.WHITE);
        financeiro.setFont(new Font("Segoe UI", Font.BOLD, 22));
        financeiro.setBounds(25,350,200,30);

        sidebar.add(financeiro);

        // TITULO
        JLabel titulo = new JLabel("Clientes & Pets");

        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));

        titulo.setForeground(new Color(74,99,255));

        titulo.setBounds(260,30,300,40);

        add(titulo);

        // BOTAO NOVO CLIENTE
        JPanel novoCliente = new JPanel();

        novoCliente.setBackground(new Color(74,99,255));

        novoCliente.setBounds(760,30,170,40);

        novoCliente.setLayout(null);

        JLabel txtBotao = new JLabel("+ Novo Cliente");

        txtBotao.setForeground(Color.WHITE);

        txtBotao.setBounds(30,10,150,20);

        novoCliente.add(txtBotao);

        add(novoCliente);

        // BUSCA
        JTextField busca = new JTextField("Buscar cliente ou pet...");

        busca.setBounds(260,100,300,40);

        busca.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));

        add(busca);

        // CARDS
        JPanel card1 = criarCard(
                "João Silva",
                "Thor",
                "Golden Retriever",
                "Plano"
        );

        card1.setBounds(260,180,680,90);

        add(card1);

        JPanel card2 = criarCard(
                "Maria Souza",
                "Luna",
                "Shih-tzu",
                "Avulso"
        );

        card2.setBounds(260,290,680,90);

        add(card2);

        JPanel card3 = criarCard(
                "Carlos Lima",
                "Mel",
                "Poodle",
                "Plano"
        );

        card3.setBounds(260,400,680,90);

        add(card3);
    }

    private JPanel criarCard(String tutor,
                             String pet,
                             String raca,
                             String tipo) {

        JPanel card = new JPanel();

        card.setLayout(null);

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220,220,220)
                )
        );

        JLabel tutorLabel = new JLabel(tutor);

        tutorLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );

        tutorLabel.setBounds(20,10,300,25);

        card.add(tutorLabel);

        JLabel petLabel = new JLabel(
                "Pet: " + pet + " • " + raca
        );

        petLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 15)
        );

        petLabel.setBounds(20,45,300,20);

        card.add(petLabel);

        JPanel tipoPanel = new JPanel();

        if(tipo.equals("Plano")) {

            tipoPanel.setBackground(
                    new Color(46,204,113)
            );

        } else {

            tipoPanel.setBackground(
                    new Color(241,196,15)
            );
        }

        tipoPanel.setBounds(520,25,120,35);

        JLabel tipoLabel = new JLabel(tipo);

        tipoLabel.setForeground(Color.WHITE);

        tipoPanel.add(tipoLabel);

        card.add(tipoPanel);

        return card;
    }

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {

            new ClientesTela().setVisible(true);

        });
    }
}