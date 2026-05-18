package com.petsync.telas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FinanceiroTela extends JFrame {

    public FinanceiroTela() {

        setTitle("PetSync - Financeiro");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        getContentPane().setBackground(
                new Color(245,247,255)
        );

        // SIDEBAR
        JPanel sidebar = new JPanel();

        sidebar.setLayout(null);

        sidebar.setBackground(
                new Color(168,177,255)
        );

        sidebar.setBounds(0,0,220,600);

        add(sidebar);

        JLabel logo = new JLabel("PETSYNC");

        logo.setFont(
                new Font("Segoe UI", Font.BOLD, 28)
        );

        logo.setForeground(Color.WHITE);

        logo.setBounds(30,30,200,40);

        sidebar.add(logo);

        JLabel sub = new JLabel("Pet e Shop");

        sub.setForeground(Color.WHITE);

        sub.setBounds(70,65,100,20);

        sidebar.add(sub);

        adicionarMenu(sidebar,
                "Dashboard",
                140);

        adicionarMenu(sidebar,
                "Agendamentos",
                210);

        adicionarMenu(sidebar,
                "Clientes & Pets",
                280);

        adicionarMenu(sidebar,
                "Financeiro",
                350);

        // TITULO
        JLabel titulo = new JLabel("Financeiro");

        titulo.setFont(
                new Font("Segoe UI", Font.BOLD, 28)
        );

        titulo.setForeground(
                new Color(74,99,255)
        );

        titulo.setBounds(260,30,250,40);

        add(titulo);

        // CARDS
        JPanel faturamento = criarCard(
                "Faturamento Mensal",
                "R$ 12.450"
        );

        faturamento.setBounds(260,110,220,120);

        add(faturamento);

        JPanel planos = criarCard(
                "Planos Ativos",
                "48"
        );

        planos.setBounds(520,110,220,120);

        add(planos);

        JPanel servicos = criarCard(
                "Serviços Realizados",
                "126"
        );

        servicos.setBounds(780,110,180,120);

        add(servicos);

        // TITULO LANCAMENTOS
        JLabel lancamentos = new JLabel(
                "Lançamentos Recentes"
        );

        lancamentos.setFont(
                new Font("Segoe UI", Font.BOLD, 20)
        );

        lancamentos.setForeground(
                new Color(74,99,255)
        );

        lancamentos.setBounds(260,270,300,30);

        add(lancamentos);

        // LISTA
        JPanel item1 = criarLancamento(
                "Banho - Thor",
                "R$ 70,00"
        );

        item1.setBounds(260,320,680,70);

        add(item1);

        JPanel item2 = criarLancamento(
                "Banho + Tosa - Luna",
                "R$ 120,00"
        );

        item2.setBounds(260,410,680,70);

        add(item2);

        JPanel item3 = criarLancamento(
                "Tosa - Mel",
                "R$ 55,00"
        );

        item3.setBounds(260,500,680,70);

        add(item3);
    }

    private void adicionarMenu(JPanel painel,
                               String texto,
                               int y) {

        JLabel label = new JLabel(texto);

        label.setForeground(Color.WHITE);

        label.setFont(
                new Font("Segoe UI", Font.BOLD, 22)
        );

        label.setBounds(25,y,220,30);

        painel.add(label);
    }

    private JPanel criarCard(String titulo,
                             String valor) {

        JPanel card = new JPanel();

        card.setLayout(null);

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220,220,220)
                )
        );

        JLabel tituloLabel = new JLabel(titulo);

        tituloLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 16)
        );

        tituloLabel.setForeground(
                new Color(74,99,255)
        );

        tituloLabel.setBounds(20,20,200,20);

        card.add(tituloLabel);

        JLabel valorLabel = new JLabel(valor);

        valorLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 28)
        );

        valorLabel.setBounds(20,55,180,40);

        card.add(valorLabel);

        return card;
    }

    private JPanel criarLancamento(String servico,
                                   String valor) {

        JPanel painel = new JPanel();

        painel.setLayout(null);

        painel.setBackground(Color.WHITE);

        painel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220,220,220)
                )
        );

        JLabel servicoLabel = new JLabel(servico);

        servicoLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );

        servicoLabel.setBounds(20,20,300,25);

        painel.add(servicoLabel);

        JLabel valorLabel = new JLabel(valor);

        valorLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 18)
        );

        valorLabel.setForeground(
                new Color(46,204,113)
        );

        valorLabel.setBounds(560,20,120,25);

        painel.add(valorLabel);

        return painel;
    }

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> {

            new FinanceiroTela().setVisible(true);

        });
    }
}